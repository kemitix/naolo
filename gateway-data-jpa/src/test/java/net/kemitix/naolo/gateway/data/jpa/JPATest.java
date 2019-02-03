package net.kemitix.naolo.gateway.data.jpa;

import net.jqwik.api.*;
import net.kemitix.naolo.core.VeterinarianRepository;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.gateway.data.common.EntityManagerProducer;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

class JPATest implements WithAssertions {

    private final DataConfig dataConfig = new DataConfig();
    private final String unitName = dataConfig.gatewayUnitName();
    private final EntityManagerProducer entityManagerProducer = new EntityManagerProducer(unitName);
    private final EntityManagerFactory entityManagerFactory = entityManagerProducer.entityManagerFactory();
    private final EntityManager entityManager = entityManagerProducer.entityManager(entityManagerFactory);
    private final VeterinarianRepository veterinarianRepository = new VeterinarianRepositoryImpl(entityManager);

    @Provide
    static Arbitrary<List<Tuple.Tuple2<VeterinarianJPA, Veterinarian>>> vetTuples() {
        final Arbitrary<Long> ids = Arbitraries.longs();
        final Arbitrary<String> names = Arbitraries.strings().alpha().ofLength(5);
        final Arbitrary<Set<VetSpecialisation>> vetSpecialisations = Arbitraries.of(VetSpecialisation.class)
                .set().ofMinSize(0).ofMaxSize(VetSpecialisation.values().length);
        return Combinators.combine(ids, names, vetSpecialisations)
                .as((id, name, specialisations) -> Tuple.of(
                        new VeterinarianJPA(id, name, specialisations.stream()
                                .map(VetSpecialisationJPA::new)
                                .collect(Collectors.toSet())),
                        Veterinarian.create(id, name, specialisations)
                )
                ).list().ofMinSize(0).ofMaxSize(100);
    }

    @Test
    void canFindAllVets() {
        //given
        final List<Tuple.Tuple2<VeterinarianJPA, Veterinarian>> vetTuples = givenLoadedTuples().stream()
                .map(t -> Tuple.of(
                        new VeterinarianJPA(t.get1(), t.get2(), t.get3().stream().map(VetSpecialisationJPA::new).collect(Collectors.toSet())),
                        new Veterinarian(t.get1(), t.get2(), t.get3())))
                .collect(Collectors.toList());
        //when
        final List<Veterinarian> result = veterinarianRepository.findAll().collect(Collectors.toList());
        //then
        assertThat(result)
                .extracting(Veterinarian::getId)
                .containsExactlyElementsOf(
                        vetTuples.stream()
                                .map(Tuple.Tuple2::get2)
                                .map(Veterinarian::getId)
                                .collect(Collectors.toList()));
        assertThat(result)
                .extracting(Veterinarian::getName)
                .containsExactlyElementsOf(
                        vetTuples.stream()
                                .map(Tuple.Tuple2::get2)
                                .map(Veterinarian::getName)
                                .collect(Collectors.toList()));
        assertThat(result)
                .extracting(Veterinarian::getSpecialisations)
                .containsExactlyElementsOf(
                        vetTuples.stream()
                                .map(Tuple.Tuple2::get2)
                                .map(Veterinarian::getSpecialisations)
                                .collect(Collectors.toList()));
    }

    private static List<Tuple.Tuple3<Long, String, Set<VetSpecialisation>>> givenLoadedTuples() {
        return asList(
                Tuple.of(1L, "Conah Feeney", singleton(VetSpecialisation.RADIOLOGY)),
                Tuple.of(2L, "Austin Santiago", singleton(VetSpecialisation.DENTISTRY)),
                Tuple.of(3L, "Violet Holmes", asSet(asList(VetSpecialisation.DENTISTRY, VetSpecialisation.SURGERY))),
                Tuple.of(4L, "Garin Charlton", singleton(VetSpecialisation.RADIOLOGY)),
                Tuple.of(5L, "Danyl Wright", emptySet()),
                Tuple.of(6L, "Savannah Sexton", singleton(VetSpecialisation.RADIOLOGY)),
                Tuple.of(7L, "Hareem Sheppard", emptySet()),
                Tuple.of(8L, "Livia Wilkins", emptySet()),
                Tuple.of(9L, "Calum Langley", asSet(asList(VetSpecialisation.RADIOLOGY, VetSpecialisation.DENTISTRY, VetSpecialisation.SURGERY))),
                Tuple.of(10L, "Rafael Mackenzie", emptySet())
        );
    }

    private static <T> Set<T> asSet(final List<T> items) {
        return new HashSet<>(items);
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

}
