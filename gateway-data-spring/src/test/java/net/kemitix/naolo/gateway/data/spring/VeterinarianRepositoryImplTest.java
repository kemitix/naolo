package net.kemitix.naolo.gateway.data.spring;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.LongArbitrary;
import net.jqwik.api.arbitraries.StringArbitrary;
import net.kemitix.naolo.core.VeterinarianRepository;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.test.StreamZipper;
import org.assertj.core.api.WithAssertions;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class VeterinarianRepositoryImplTest implements WithAssertions {

    private static final int MAX_VETERINARIANS = 100;

    private final VeterinarianRepositorySpring springRepo = mock(VeterinarianRepositorySpring.class);

    private final VeterinarianRepository repository =
            new VeterinarianRepositoryImpl(springRepo);

    @Property
    void canFindAllVeterinarians(@ForAll("jpaVets") final List<VeterinarianJPA> jpaVets) {
        //given
        given(springRepo.findAll()).willReturn(jpaVets);
        //when
        final List<Veterinarian> result = repository.findAll().collect(Collectors.toList());
        //then
        assertThat(result).hasSize(jpaVets.size());
        assertThat(StreamZipper.zip(jpaVets, result, Tuple::of))
                .allSatisfy(t -> {
                    final VeterinarianJPA jpa = t.get1();
                    final Veterinarian vet = t.get2();
                    assertThat(jpa.getId()).isEqualTo(vet.getId());
                    assertThat(jpa.getName()).isEqualToIgnoringCase(vet.getName());
                    assertThat(jpa.getSpecialisations())
                            .isEqualTo(vet.getSpecialisations());
                });
    }

    @Provide
    static Arbitrary<List<VeterinarianJPA>> jpaVets() {
        final LongArbitrary ids = Arbitraries.longs();
        final StringArbitrary names = Arbitraries.strings();
        final Arbitrary<Set<VetSpecialisation>> specialisations =
                Arbitraries.of(VetSpecialisation.class)
                        .set().ofMinSize(0).ofMaxSize(VetSpecialisation.values().length);
        return Combinators.combine(ids, names, specialisations)
                .as(VeterinarianJPA::new)
                .list().ofMinSize(0).ofMaxSize(MAX_VETERINARIANS);
    }

}