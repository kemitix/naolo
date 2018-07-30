package net.kemitix.naolo.entities;

import net.jqwik.api.*;
import org.assertj.core.api.WithAssertions;

import java.util.Set;

class VeterinarianTest implements WithAssertions {

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
    Arbitrary<Set<VetSpecialisation>> specialisation() {
        return Arbitraries.of(VetSpecialisation.class)
                .set().ofMinSize(0).ofMaxSize(VetSpecialisation.values().length);
    }
}