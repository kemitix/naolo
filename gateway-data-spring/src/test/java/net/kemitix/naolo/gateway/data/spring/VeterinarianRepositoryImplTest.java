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
        assertThat(StreamZipper.zip(jpaVets, result, Tuples::tuple))
                .allSatisfy(t -> {
                    final VeterinarianJPA jpa = t.get1();
                    final Veterinarian vet = t.get2();
                    assertThat(jpa.getId()).isEqualTo(vet.getId());
                    assertThat(jpa.getName()).isEqualToIgnoringCase(vet.getName());
                    assertThat(jpa.getSpecialisations())
                            .isEqualTo(vet.getSpecialisations().stream()
                                    .map(Enum::toString).collect(Collectors.toSet()));
                });
    }

    @Provide
    static Arbitrary<List<VeterinarianJPA>> jpaVets() {
        final LongArbitrary ids = Arbitraries.longs();
        final StringArbitrary names = Arbitraries.strings();
        final Arbitrary<Set<String>> specialisations =
                Arbitraries.of(VetSpecialisation.class)
                        .map(Enum::toString)
                        .set().ofMinSize(0).ofMaxSize(VetSpecialisation.values().length);
        return Combinators.combine(ids, names, specialisations)
                .as(VeterinarianJPA::new)
                .list().ofMinSize(0).ofMaxSize(MAX_VETERINARIANS);
    }

    //    @Property
    //    void canConvertFromJpa(
    //            @ForAll final Long id,
    //            @ForAll final String name,
    //            @ForAll("specialisationStrings") final Set<String> specialisations
    //    ) {
    //        //given
    //        final VeterinarianJPA jpa = new VeterinarianJPA(id, name, specialisations);
    //        //when
    //        final Veterinarian result = fromJPA.convert(jpa);
    //        //then
    //        assertThat(result)
    //                .returns(id, Veterinarian::getId)
    //                .returns(name, Veterinarian::getName)
    //                .returns(specialisations, v -> v.getSpecialisations().stream()
    //                        .map(Enum::toString).collect(Collectors.toSet()));
    //    }
    //
    //    @Property
    //    void canConvertToJpa(
    //            @ForAll final Long id,
    //            @ForAll final String name,
    //            @ForAll("specialisations") final Set<VetSpecialisation> specialisations
    //    ) {
    //        //given
    //        final Veterinarian veterinarian = Veterinarian.create(id, name, specialisations);
    //        //when
    //        final VeterinarianJPA result = toJPA.convert(veterinarian);
    //        //then
    //        assertThat(result)
    //                .returns(id, VeterinarianJPA::getId)
    //                .returns(name, VeterinarianJPA::getName)
    //                .returns(specialisations, s -> s.getSpecialisations().stream()
    //                        .map(VetSpecialisation::valueOf).collect(Collectors.toSet()));
    //    }
    //
    //    @Provide
    //    static Arbitrary<Set<String>> specialisationStrings() {
    //        return Arbitraries.of(VetSpecialisation.class)
    //                .set()
    //                .ofMinSize(0).ofMaxSize(VetSpecialisation.values().length)
    //                .map(v -> v.stream().map(Enum::toString).collect(Collectors.toSet()));
    //    }
    //
    //    @Provide
    //    static Arbitrary<Set<VetSpecialisation>> specialisations() {
    //        return Arbitraries.of(VetSpecialisation.class)
    //                .set().ofMinSize(0).ofMaxSize(3);
    //    }
}