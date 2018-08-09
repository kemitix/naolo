package net.kemitix.naolo.entities;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Providers of Arbitrary values for use with jqwik property based tests.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
public class ArbitrariesHelper {

    /**
     * Generator of Arbitrary ZonedDataTime values.
     *
     * <p>This implementation uses values generated from {@link Instant} which have a slightly larger value range than
     * {@link ZonedDateTime}. The range for {@link Instant} extends one year before and and after that of
     * {@link ZonedDateTime}. The range of {@link Instant} values used is therefor reduced to fit within that of
     * {@link ZonedDateTime}.</p>
     *
     * @return an Arbitrary value generator for ZonedDateTime values
     */
    public static Arbitrary<ZonedDateTime> zonedDateTime() {
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
