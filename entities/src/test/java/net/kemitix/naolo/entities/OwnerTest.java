package net.kemitix.naolo.entities;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.assertj.core.api.WithAssertions;

class OwnerTest implements WithAssertions {

    @Property
    void hasGetters(
            @ForAll final Long id,
            @ForAll final String firstName,
            @ForAll final String lastName,
            @ForAll final String street,
            @ForAll final String city,
            @ForAll final String telephone
    ) {
        //given
        final Owner owner = Owner.create(id, firstName, lastName, street, city, telephone);
        //then
        assertThat(owner)
                .returns(id, Owner::getId)
                .returns(firstName, Owner::getFirstName)
                .returns(lastName, Owner::getLastName)
                .returns(street, Owner::getStreet)
                .returns(city, Owner::getCity)
                .returns(telephone, Owner::getTelephone);
    }
}