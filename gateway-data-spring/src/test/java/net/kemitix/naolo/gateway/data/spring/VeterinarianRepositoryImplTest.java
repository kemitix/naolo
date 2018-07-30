package net.kemitix.naolo.gateway.data.spring;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.LongArbitrary;
import net.jqwik.api.arbitraries.StringArbitrary;
import net.kemitix.naolo.core.VeterinarianRepository;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import org.assertj.core.api.WithAssertions;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class VeterinarianRepositoryImplTest implements WithAssertions {

    private static final int MAX_VETERINARIANS = 100;

    private final VeterinarianRepositorySpring springRepo = mock(VeterinarianRepositorySpring.class);
    private final Converter<VeterinarianJPA, Veterinarian> fromJpaConverter = new VeterinarianRepositoryImpl.FromJPA();
    private final Converter<Veterinarian, VeterinarianJPA> toJpaConverter = new VeterinarianRepositoryImpl.ToJPA();

    private final VeterinarianRepository repository = new VeterinarianRepositoryImpl(springRepo, fromJpaConverter);

    @Property
    void canFindAllVeterinarians(@ForAll("jpaVets") final List<VeterinarianJPA> jpaVets) {
        //given
        given(springRepo.findAll()).willReturn(jpaVets);
        //when
        final List<Veterinarian> result = repository.findAll().collect(Collectors.toList());
        //then
        assertThat(result)
                .extracting(toJpaConverter::convert)
                .containsAll(jpaVets);
    }

    @Provide
    Arbitrary<List<VeterinarianJPA>> jpaVets() {
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
}