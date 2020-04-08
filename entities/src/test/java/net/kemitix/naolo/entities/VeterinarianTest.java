package net.kemitix.naolo.entities;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

public class VeterinarianTest
        implements WithAssertions {

    @Test
    @DisplayName("Usage Syntax")
    public void usageSyntax() {
        //given
        final long id = 23L;
        final String name = "Name";
        final Set<VetSpecialisation> specialisations =
                Collections.singleton(VetSpecialisation.RADIOLOGY);
        //when
        final Veterinarian allArgs =
                new Veterinarian(id, name, specialisations);
        final Veterinarian noArgsWith =
                new Veterinarian()
                        .withId(id)
                        .withName(name)
                        .withSpecialisations(specialisations);
        final Veterinarian builder =
                Veterinarian.builder()
                        .id(id)
                        .name(name)
                        .specialisations(specialisations)
                        .build();
        //then
        assertThat(allArgs).isEqualTo(noArgsWith);
        assertThat(allArgs).isEqualTo(builder);
    }
}