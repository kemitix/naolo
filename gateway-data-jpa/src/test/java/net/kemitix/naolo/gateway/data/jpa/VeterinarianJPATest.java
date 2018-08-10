package net.kemitix.naolo.gateway.data.jpa;

import net.jqwik.api.*;
import net.kemitix.naolo.entities.VetSpecialisation;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class VeterinarianJPATest implements WithAssertions {

    @Provide
    static Arbitrary<Set<VetSpecialisationJPA>> vetSpecialisations() {
        return Arbitraries.of(VetSpecialisation.class)
                .map(VetSpecialisationJPA::new)
                .set().ofMinSize(0).ofMaxSize(VetSpecialisation.values().length);
    }

    @Property
    void hasAllArgsConstructor(
            @ForAll final Long id,
            @ForAll final String name,
            @ForAll("vetSpecialisations") final Set<VetSpecialisationJPA> specialisations
    ) {
        assertThat(new VeterinarianJPA(id, name, specialisations)).isNotNull();
    }

    @Test
    void hasNoArgsConstructor() {
        assertThat(new VeterinarianJPA()).isNotNull();
    }

}