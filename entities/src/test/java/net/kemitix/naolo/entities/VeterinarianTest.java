package net.kemitix.naolo.entities;

import net.jqwik.api.*;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

class VeterinarianTest implements WithAssertions {

    @Test
    void hasDefaultConstructor() {
        assertThat(Veterinarian.create()).isNotNull();
    }

    @Property
    void hasStringSpecialisationsConstructor(
            @ForAll Long id,
            @ForAll String name,
            @ForAll("specialisation") Set<VetSpecialisation> specialisations
    ) {
        //given
        final String specString = specialisations.stream().map(Enum::toString).collect(Collectors.joining(";"));
        //when
        final Veterinarian veterinarian = Veterinarian.create(id, name, specString);
        //then
        assertThat(veterinarian.getSpecialisations()).containsExactlyElementsOf(specialisations);
    }

    @Property
    void hasGetters(
            @ForAll Long id,
            @ForAll String name,
            @ForAll("specialisation") Set<VetSpecialisation> specialisations
    ) {
        //given
        final Veterinarian veterinarian = Veterinarian.create(id, name, specialisations);
        //then
        assertThat(veterinarian)
                .returns(id, Veterinarian::getId)
                .returns(name, Veterinarian::getName)
                .returns(specialisations, Veterinarian::getSpecialisations);
    }

    @Provide
    static Arbitrary<Set<VetSpecialisation>> specialisation() {
        return Arbitraries.of(VetSpecialisation.class)
                .set().ofMinSize(0).ofMaxSize(VetSpecialisation.values().length);
    }
}