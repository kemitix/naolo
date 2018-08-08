package net.kemitix.naolo.entities;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import org.assertj.core.api.WithAssertions;

import java.time.ZonedDateTime;

class PetTest implements WithAssertions {

    @Property
    void hasGetters(
            @ForAll final long id,
            @ForAll final String name,
            @ForAll("zonedDateTime") final ZonedDateTime dateOfBirth,
            @ForAll final PetType type,
            @ForAll final long ownerId
    ) {
        //given
        final Pet pet = Pet.create(id, name, dateOfBirth, type, ownerId);
        //then
        assertThat(pet)
                .returns(id, Pet::getId)
                .returns(name, Pet::getName)
                .returns(dateOfBirth, Pet::getDateOfBirth)
                .returns(type, Pet::getType)
                .returns(ownerId, Pet::getOwnerId);
    }

    @Provide
    static Arbitrary<ZonedDateTime> zonedDateTime() {
        return ArbitraryProviders.zonedDateTime();
    }

}