package net.kemitix.naolo.core;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.LongArbitrary;
import net.jqwik.api.arbitraries.SizableArbitrary;
import net.jqwik.api.arbitraries.StringArbitrary;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.test.StreamZipper;
import org.assertj.core.api.WithAssertions;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class VeterinariansListAllTest implements WithAssertions {

    private final VeterinarianRepository repository = mock(VeterinarianRepository.class);

    private final VeterinariansListAll usecase = VeterinariansListAll.create(repository);

    private final VeterinariansListAll.Request request = new VeterinariansListAll.Request();

    @Property
    void listAll(
            @ForAll("vets") final List<Veterinarian> vets
    ) {
        //given
        given(repository.findAll()).willReturn(vets.stream());
        //when
        final VeterinariansListAll.Response response = usecase.invoke(request);
        //then
        final Stream<Tuples.Tuple2<Veterinarian, Veterinarian>> zipped = StreamZipper.zip(vets, response.getAllVeterinarians(), Tuples::tuple);
        assertThat(zipped).hasSize(vets.size())
                .allSatisfy(t -> {
                    final Veterinarian s = t.get1();
                    final Veterinarian r = t.get2();
                    assertThat(r.getId()).isEqualTo(s.getId());
                    assertThat(r.getName()).isEqualTo(s.getName());
                    assertThat(r.getSpecialisations()).isEqualTo(s.getSpecialisations());
                });
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