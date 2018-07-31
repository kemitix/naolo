package net.kemitix.naolo.presenter.rest.jaxrs;

import net.jqwik.api.*;
import net.kemitix.naolo.core.VeterinarianRepository;
import net.kemitix.naolo.core.VeterinariansListAll;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author Paul Campbell (pcampbell@kemitix.net)
 */

class VeterinariansControllerTest implements WithAssertions {

    private static final int MAX_VETERINARIANS = 100;

    private final VeterinarianRepository veterinarianRepository = mock(VeterinarianRepository.class);
    private final VeterinariansController controller = new VeterinariansController(new VeterinariansListAll(veterinarianRepository));

    @Test
    void defaultConstructorForController() {
        //when
        final VeterinariansController controller = new VeterinariansController();
        //then
        assertThat(controller).isNotNull();
    }

    @Property
    @SuppressWarnings("unchecked")
    void canGetAllVets(
            @ForAll("vets") final List<Veterinarian> vets
    ) {
        //given
        given(veterinarianRepository.findAll()).willReturn(vets.stream());
        //when
        final Response response = controller.allVets();
        //then
        final List<Veterinarian> entity = (List<Veterinarian>) response.getEntity();
        assertThat(entity).hasSize(vets.size());
    }

    @Provide
    static Arbitrary<List<Veterinarian>> vets() {
        final Arbitrary<Long> ids = Arbitraries.longs();
        final Arbitrary<String> names = Arbitraries.strings();
        final Arbitrary<Set<VetSpecialisation>> specialities = Arbitraries.of(VetSpecialisation.class)
                .set().ofMinSize(0).ofMaxSize(3);
        return Combinators.combine(ids, names, specialities)
                .as(Veterinarian::create).list().ofMaxSize(MAX_VETERINARIANS);
    }
}