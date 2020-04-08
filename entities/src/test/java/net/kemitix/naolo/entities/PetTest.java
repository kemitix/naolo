package net.kemitix.naolo.entities;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PetTest
        implements WithAssertions {
    @Test
    @DisplayName("Usage Syntax")
    public void usageSyntax() {
        //given
        final long id = 23L;
        final String name = "Name";
        final ZonedDateTime dateOfBirth =
                Instant.now()
                        .atZone(ZoneId.systemDefault())
                        .withFixedOffsetZone();
        final PetType type = PetType.DOG;
        final Owner owner = new Owner();
        //when
        final Pet allArgs =
                new Pet(
                        id,
                        name,
                        dateOfBirth,
                        type,
                        owner);
        final Pet noArgsWith =
                new Pet()
                        .withId(id)
                        .withName(name)
                        .withDateOfBirth(dateOfBirth)
                        .withType(type)
                        .withOwner(owner);
        final Pet builder =
                Pet.builder()
                        .id(id)
                        .name(name)
                        .dateOfBirth(dateOfBirth)
                        .type(type)
                        .owner(owner)
                        .build();
        //then
        assertThat(allArgs).isEqualTo(noArgsWith);
        assertThat(allArgs).isEqualTo(builder);
    }
}