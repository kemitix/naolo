package net.kemitix.naolo.visits;

import net.kemitix.naolo.pets.Pet;
import net.kemitix.naolo.vets.Veterinarian;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class VisitTest
        implements WithAssertions {
    @Test
    @DisplayName("Usage Syntax")
    public void usageSyntax() {
        //given
        final long id = 23L;
        final Pet pet = new Pet();
        final Veterinarian veterinarian = new Veterinarian();
        final LocalDateTime dateTime = LocalDateTime.now();
        final String description = "Description";
        //when
        final Visit allArgs =
                new Visit(
                        id,
                        pet,
                        veterinarian,
                        dateTime,
                        description);
        final Visit noArgsWith =
                new Visit()
                        .withId(id)
                        .withPet(pet)
                        .withVeterinarian(veterinarian)
                        .withDateTime(dateTime)
                        .withDescription(description);
        final Visit noArgsSetters = new Visit();
        noArgsSetters.setId(id);
        noArgsSetters.setPet(pet);
        noArgsSetters.setVeterinarian(veterinarian);
        noArgsSetters.setDateTime(dateTime);
        noArgsSetters.setDescription(description);
        //then
        assertThat(allArgs).isEqualTo(noArgsWith);
        assertThat(allArgs).isEqualTo(noArgsSetters);
        assertThat(allArgs.getId()).isEqualTo(id);
        assertThat(allArgs.getPet()).isEqualTo(pet);
        assertThat(allArgs.getVeterinarian()).isEqualTo(veterinarian);
        assertThat(allArgs.getDateTime()).isEqualTo(dateTime);
        assertThat(allArgs.getDescription()).isEqualTo(description);
        assertThat(allArgs.toString()).isEqualTo(String.format(
                "Visit(id=%d, pet=%s, veterinarian=%s, dateTime=%s, " +
                        "description=%s)",
                id, pet, veterinarian, dateTime, description));
    }
}
