package net.kemitix.naolo.entities;

import net.jqwik.api.*;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VetSpecialisationTest
        implements WithAssertions {


    @Provide
    static Arbitrary<List<VetSpecialisation>> vetSpecs() {
        return Arbitraries.of(VetSpecialisation.class)
                .list()
                .ofMinSize(0)
                .ofMaxSize(VetSpecialisation.values().length);
    }

    @Property
    public void parseString(
            @ForAll("vetSpecs") final List<VetSpecialisation> specialisations
    ) {
        final Set<VetSpecialisation> expected = new HashSet<>(specialisations);
        final String asString = specialisations.stream()
                .map(VetSpecialisation::name)
                .collect(Collectors.joining(";"));
        final Set<VetSpecialisation> result = VetSpecialisation.parse(asString);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    @DisplayName("parse an invalid value throws exception")
    public void parseInvalidString() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        VetSpecialisation.parse("garbage"));
    }
}
