package net.kemitix.naolo.presenter.rest.spring;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.LongArbitrary;
import net.jqwik.api.arbitraries.SizableArbitrary;
import net.jqwik.api.arbitraries.StringArbitrary;
import net.kemitix.naolo.core.VeterinarianRepository;
import net.kemitix.naolo.core.VeterinariansListAll;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import org.assertj.core.api.WithAssertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class VeterinariansControllerTest implements WithAssertions {

    private final VeterinarianRepository repository = mock(VeterinarianRepository.class);
    private final VeterinariansListAll veterinariansListAll = VeterinariansListAll.create(repository);
    private final VeterinariansController controller = new VeterinariansController(veterinariansListAll);

    @Property
    public void invokeListAll(
            @ForAll("vets") final List<Veterinarian> vets
    ) {
        //given
        given(repository.findAll()).willReturn(vets.stream());
        //when
        final ResponseEntity<List<Veterinarian>> listResponseEntity = controller.allVets();
        //then
        assertThat(listResponseEntity.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(listResponseEntity.getBody()).containsExactlyElementsOf(vets);
    }

    @Provide
    Arbitrary<List<Veterinarian>> vets() {
        final LongArbitrary ids = Arbitraries.longs();
        final StringArbitrary names = Arbitraries.strings();
        final SizableArbitrary<Set<VetSpecialisation>> specialisations = Arbitraries.of(VetSpecialisation.class)
                .set().ofMinSize(0).ofMaxSize(3);
        return Combinators.combine(ids, names, specialisations)
                .as(Veterinarian::create)
                .list();
    }
}