package net.kemitix.naolo.entities;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

class ArbitraryProviders {

    static Arbitrary<ZonedDateTime> zonedDateTime() {
        // Instant supports a range a year before and after that supported by ZonedDateTime
        final Instant minInstantAllowed = Instant.MIN.plus(367, ChronoUnit.DAYS);
        final Instant maxInstantAllowed = Instant.MAX.minus(367, ChronoUnit.DAYS);
        return Arbitraries.longs()
                .between(
                        minInstantAllowed.getEpochSecond(),
                        maxInstantAllowed.getEpochSecond())
                .map(Instant::ofEpochSecond)
                .map(i -> ZonedDateTime.ofInstant(i, ZoneId.systemDefault()));
    }
}
