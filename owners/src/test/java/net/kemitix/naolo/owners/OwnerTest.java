package net.kemitix.naolo.owners;

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
        final Owner noArgsSetters = new Owner();
        noArgsSetters.setId(id);
        noArgsSetters.setFirstName(firstName);
        noArgsSetters.setLastName(lastName);
        noArgsSetters.setStreet(street);
        noArgsSetters.setCity(city);
        //then
        assertThat(allArgs).isEqualTo(noArgsWith);
        assertThat(allArgs).isEqualTo(noArgsSetters);
        assertThat(allArgs.getId()).isEqualTo(id);
        assertThat(allArgs.getFirstName()).isEqualTo(firstName);
        assertThat(allArgs.getLastName()).isEqualTo(lastName);
        assertThat(allArgs.getStreet()).isEqualTo(street);
        assertThat(allArgs.getCity()).isEqualTo(city);
        assertThat(allArgs.toString())
                .isEqualTo(String.format(
                        "Owner(id=%d, firstName=%s, lastName=%s, street=%s, " +
                                "city=%s)",
                        id, firstName, lastName, street, city));
    }
}