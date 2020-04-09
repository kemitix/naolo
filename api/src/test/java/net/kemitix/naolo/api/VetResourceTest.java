package net.kemitix.naolo.api;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.kemitix.naolo.core.vets.AddVet;
import net.kemitix.naolo.core.vets.ListAllVets;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;
import org.assertj.core.api.WithAssertions;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
public class VetResourceTest implements WithAssertions {

    private final VeterinarianRepository veterinarianRepository =
            mock(VeterinarianRepository.class);
    private final VetResource controller =
            new VetResource(
                    new ListAllVets(veterinarianRepository),
                    new AddVet(veterinarianRepository));
    private final Long nextId = new Random().nextLong();

    @Property
    @SuppressWarnings("unchecked")
    public void canGetAllVets(
            @ForAll final List<Veterinarian> vets
    ) throws ExecutionException, InterruptedException {
        //given
        given(veterinarianRepository.findAll()).willReturn(vets.stream());
        //when
        final Response response = controller.allVets();
        //then
        final List<Veterinarian> entity =
                (List<Veterinarian>) response.getEntity();
        assertThat(entity).hasSize(vets.size());
    }

    @Property
    public void canAddVet(
            @ForAll final Veterinarian vet
    ) {
        //given
        given(veterinarianRepository.add(any(Veterinarian.class)))
                .willAnswer(call ->
                    ((Veterinarian) call.getArgument(0))
                            .withId(nextId));
        //when
        final Response response = controller.add(vet);
        //then
        assertThat(response.hasEntity()).isTrue();
        assertThat(((Veterinarian) response.getEntity()))
                .extracting(Veterinarian::getId)
                .isNotNull();
    }
}
