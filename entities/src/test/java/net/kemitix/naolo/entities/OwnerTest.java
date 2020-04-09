package net.kemitix.naolo.entities;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OwnerTest
        implements WithAssertions {
    @Test
    @DisplayName("Usage Syntax")
    public void usageSyntax() {
        //given
        final long id = 23L;
        final String firstName = "First Name";
        final String lastName = "Last Name";
        final String street = "Street";
        final String city = "City";
        //when
        final Owner allArgs =
                new Owner(
                        id,
                        firstName,
                        lastName,
                        street,
                        city);
        final Owner noArgsWith =
                new Owner()
                        .withId(id)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withStreet(street)
                        .withCity(city);
        final Owner builder =
                Owner.builder()
                        .id(id)
                        .firstName(firstName)
                        .lastName(lastName)
                        .street(street)
                        .city(city)
                        .build();
        //then
        assertThat(allArgs).isEqualTo(noArgsWith);
        assertThat(allArgs).isEqualTo(builder);
    }
}