package net.kemitix.naolo.entities;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import org.assertj.core.api.WithAssertions;

import java.time.ZonedDateTime;

class VisitTest implements WithAssertions {

    @Property
    void hasGetters(
            @ForAll final long id,
            @ForAll final long petId,
            @ForAll final long vetId,
            @ForAll("zonedDateTime") final ZonedDateTime dateTime,
            @ForAll final String description
    ) {
        //given
        final Visit visit = Visit.create(id, petId, vetId, dateTime, description);
        //then
        assertThat(visit)
                .returns(id, Visit::getId)
                .returns(petId, Visit::getPetId)
                .returns(vetId, Visit::getVetId)
                .returns(dateTime, Visit::getDateTime)
                .returns(description, Visit::getDescription);
    }

    @Provide
    static Arbitrary<ZonedDateTime> zonedDateTime() {
        return ArbitraryProviders.zonedDateTime();
    }

}