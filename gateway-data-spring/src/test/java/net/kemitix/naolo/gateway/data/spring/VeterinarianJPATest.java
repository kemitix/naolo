package net.kemitix.naolo.gateway.data.spring;

import net.jqwik.api.*;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class VeterinarianJPATest implements WithAssertions {

    @Property
    void requireName(
            @ForAll final Long id,
            @ForAll final Set<String> specialisations
    ) {
        assertThatNullPointerException().isThrownBy(() ->
                new VeterinarianJPA(id, null, specialisations));
    }

    @Property
    void requireName(
            @ForAll final Long id,
            @ForAll final String name
    ) {
        assertThatNullPointerException().isThrownBy(() ->
                new VeterinarianJPA(id, name, null));
    }

    @Property
    void hasGetters(
            @ForAll final Long id,
            @ForAll final String name,
            @ForAll final Set<String> specialisations
    ) {
        //given
        final VeterinarianJPA jpa = new VeterinarianJPA(id, name, specialisations);
        //then
        assertThat(jpa)
                .returns(id, VeterinarianJPA::getId)
                .returns(name, VeterinarianJPA::getName)
                .returns(specialisations, VeterinarianJPA::getSpecialisations);
    }

    @Provide
    Arbitrary<VeterinarianJPA> jpa() {
        final Arbitrary<Long> ids = Arbitraries.longs();
        final Arbitrary<String> names = Arbitraries.strings();
        final Arbitrary<Set<String>> specialisations = Arbitraries.strings()
                .set().ofMinSize(0).ofMaxSize(3);
        return Combinators.combine(ids, names, specialisations)
                .as(VeterinarianJPA::new);
    }
}