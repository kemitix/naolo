package net.kemitix.naolo.gateway.data.deltaspike;

import net.jqwik.api.*;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeltaSpikeTest implements WithAssertions {

    private final EntityManagerProducer entityManagerProducer = new EntityManagerProducer();
    private final EntityManagerFactory entityManagerFactory = entityManagerProducer.entityManagerFactory();
    private final EntityManager entityManager = entityManagerProducer.entityManager(entityManagerFactory);
    private final VeterinarianRepositoryDeltaSpike veterinarianRepositoryDeltaSpike =
            mock(VeterinarianRepositoryDeltaSpike.class);
    private final VeterinarianRepositoryImpl veterinarianRepository =
            new VeterinarianRepositoryImpl(veterinarianRepositoryDeltaSpike);

    @Provide
    static Arbitrary<List<Tuples.Tuple2<VeterinarianJPA, Veterinarian>>> vetTuples() {
        final Arbitrary<Long> ids = Arbitraries.longs();
        final Arbitrary<String> names = Arbitraries.strings();
        final Arbitrary<Set<VetSpecialisation>> vetSpecialisations = Arbitraries.of(VetSpecialisation.class)
                .set().ofMinSize(0).ofMaxSize(VetSpecialisation.values().length);
        return Combinators.combine(ids, names, vetSpecialisations)
                .as((id, name, specialisations) -> {
                            final VeterinarianJPA veterinarianJPA = new VeterinarianJPA();
                            veterinarianJPA.setId(id);
                            veterinarianJPA.setName(name);
                            veterinarianJPA.getSpecialisations().addAll(
                                    specialisations.stream()
                                            .map(Enum::toString)
                                            .collect(Collectors.toList())
                            );
                            return Tuples.tuple(
                                    veterinarianJPA,
                                    Veterinarian.create(id, name, specialisations)
                            );
                        }
                ).list().ofMinSize(0).ofMaxSize(100);
    }

    @Property
    void deltaSpikeTes(
            @ForAll("vetTuples") List<Tuples.Tuple2<VeterinarianJPA, Veterinarian>> vetTuples
    ) {
        //given
        final List<VeterinarianJPA> veterinarians =
                vetTuples.stream().map(Tuples.Tuple2::get1).collect(Collectors.toList());
        given(veterinarianRepositoryDeltaSpike.findAll()).willReturn(veterinarians.stream());
        //when
        final List<Veterinarian> result = veterinarianRepository.findAll().collect(Collectors.toList());
        //then
        assertThat(result)
                .extracting(Veterinarian::getId)
                .containsExactlyElementsOf(
                        vetTuples.stream()
                                .map(Tuples.Tuple2::get2)
                                .map(Veterinarian::getId)
                                .collect(Collectors.toList()));
        assertThat(result)
                .extracting(Veterinarian::getName)
                .containsExactlyElementsOf(
                        vetTuples.stream()
                                .map(Tuples.Tuple2::get2)
                                .map(Veterinarian::getName)
                                .collect(Collectors.toList()));
        assertThat(result)
                .extracting(Veterinarian::getSpecialisations)
                .containsExactlyElementsOf(
                        vetTuples.stream()
                                .map(Tuples.Tuple2::get2)
                                .map(Veterinarian::getSpecialisations)
                                .collect(Collectors.toList()));
    }

    @Test
    void veterinarianRepositoryImplDefaultConstructorIsInvalid() {
        //when
        final VeterinarianRepositoryImpl veterinarianRepository = new VeterinarianRepositoryImpl();
        //then
        assertThatNullPointerException()
                .isThrownBy(veterinarianRepository::findAll);
    }

    @Test
    void createEntityManagerFactory() {
        assertThat(entityManagerFactory).isNotNull();
    }

    @Test
    void canCreateUniqueEntityManagers() {
        //when
        final EntityManager entityManagerA = entityManagerProducer.entityManager(entityManagerFactory);
        final EntityManager entityManagerB = entityManagerProducer.entityManager(entityManagerFactory);
        //then
        assertThat(entityManagerA).isNotNull().isNotSameAs(entityManagerB);
    }

    @Test
    void canDisposeOfEntityManagers() {
        //given
        assertThat(entityManager.isOpen()).isTrue();
        //when
        entityManagerProducer.close(entityManager);
        //then
        assertThat(entityManager.isOpen()).isFalse();
    }

}
