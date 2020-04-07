package net.kemitix.naolo.presenter.rest.jaxrs;

import net.jqwik.api.*;
import net.kemitix.naolo.core.vets.ListAllVets;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;
import org.assertj.core.api.WithAssertions;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
public class VeterinariansControllerTest implements WithAssertions {

    private static final int MAX_VETERINARIANS = 100;

    private final VeterinarianRepository veterinarianRepository = mock(VeterinarianRepository.class);
    private final VeterinariansController controller = new VeterinariansController(new ListAllVets(veterinarianRepository));

    @Provide
    public static Arbitrary<List<Veterinarian>> vets() {
        final Arbitrary<Long> ids = Arbitraries.longs();
        final Arbitrary<String> names = Arbitraries.strings();
        final Arbitrary<Set<VetSpecialisation>> specialities = Arbitraries.of(VetSpecialisation.class)
                .set().ofMinSize(0).ofMaxSize(VetSpecialisation.values().length);
        return Combinators.combine(ids, names, specialities)
                .as((id, name, vetSpecs) ->
                        Veterinarian.builder()
                                .id(id)
                                .name(name)
                                .specialisations(vetSpecs)
                                .build())
                .list()
                .ofMaxSize(MAX_VETERINARIANS);
    }

    @Property
    @SuppressWarnings("unchecked")
    public void canGetAllVets(
            @ForAll("vets") final List<Veterinarian> vets
    ) throws ExecutionException, InterruptedException {
        //given
        given(veterinarianRepository.findAll()).willReturn(vets.stream());
        //when
        final Response response = controller.allVets();
        //then
        final List<Veterinarian> entity = (List<Veterinarian>) response.getEntity();
        assertThat(entity).hasSize(vets.size());
    }
}