package net.kemitix.naolo.vets;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class VeterinarianTest
        implements WithAssertions {

    @Test
    @DisplayName("Usage Syntax")
    public void usageSyntax() {
        //given
        final long id = 23L;
        final String name = "Name";
        final List<VetSpecialisation> specialisations =
                Collections.singletonList(VetSpecialisation.RADIOLOGY);
        //when
        final Veterinarian allArgs =
                new Veterinarian(id, name, specialisations);
        final Veterinarian noArgsWith =
                new Veterinarian()
                        .withId(id)
                        .withName(name)
                        .withSpecialisations(specialisations);
        final Veterinarian noArgsSetters = new Veterinarian();
        noArgsSetters.setId(id);
        noArgsSetters.setName(name);
        noArgsSetters.setSpecialisations(specialisations);
        //then
        assertThat(allArgs).isEqualTo(noArgsWith);
        assertThat(allArgs).isEqualTo(noArgsSetters);
        assertThat(allArgs.getId()).isEqualTo(id);
        assertThat(allArgs.getName()).isEqualTo(name);
        assertThat(allArgs.getSpecialisations()).isEqualTo(specialisations);
        assertThat(allArgs.toString())
                .isEqualTo(String.format(
                        "Veterinarian(id=%d, name=%s, specialisations=%s)",
                        id, name, specialisations));
    }
}