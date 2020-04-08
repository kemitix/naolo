package net.kemitix.naolo.entities;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class VisitTest
        implements WithAssertions {
    @Test
    @DisplayName("Usage Syntax")
    public void usageSyntax() {
        //given
        final long id = 23L;
        final Pet pet = new Pet();
        final Veterinarian veterinarian = new Veterinarian();
        final ZonedDateTime dateTime =
                Instant.now()
                        .atZone(ZoneId.systemDefault())
                        .withFixedOffsetZone();
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
        final Visit builder =
                Visit.builder()
                        .id(id)
                        .pet(pet)
                        .veterinarian(veterinarian)
                        .dateTime(dateTime)
                        .description(description)
                        .build();
        //then
        assertThat(allArgs).isEqualTo(noArgsWith);
        assertThat(allArgs).isEqualTo(builder);
    }
}
