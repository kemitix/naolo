package net.kemitix.naolo.entities;

import net.jqwik.api.*;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class VeterinarianTest implements WithAssertions {

    @Provide
    static Arbitrary<Set<VetSpecialisation>> vetSpecs() {
        return Arbitraries.of(VetSpecialisation.class)
                .set()
                .ofMinSize(0)
                .ofMaxSize(VetSpecialisation.values().length);
    }

    @Test
    void hasNamedConstructor() {
        assertThat(Veterinarian.create()).isNotNull();
    }

    @Property
    void hasGetters(
            @ForAll final Long id,
            @ForAll final String name,
            @ForAll("vetSpecs") final Set<VetSpecialisation> specialisations
    ) {
        //given
        final Veterinarian vet =
                Veterinarian.builder()
                        .id(id)
                        .name(name)
                        .specialisations(specialisations)
                        .build();
        //then
        assertThat(vet)
                .returns(id, Veterinarian::getId)
                .returns(name, Veterinarian::getName)
                .returns(specialisations, Veterinarian::getSpecialisations);
    }
}