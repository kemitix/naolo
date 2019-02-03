package net.kemitix.naolo.test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Tuple;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.stream.Collectors;

class StreamZipperTest implements WithAssertions {

    @Test
    void privateUtilityConstructor() throws NoSuchMethodException {
        //given
        final Constructor<StreamZipper> constructor = StreamZipper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        //then
        assertThatCode(constructor::newInstance)
                .hasCauseInstanceOf(UnsupportedOperationException.class);
    }

    @Property
    void pairItems(
            @ForAll List<String> strings,
            @ForAll List<Integer> integers
    ) {
        //when
        final List<Tuple.Tuple2<String, Integer>> zipped =
                StreamZipper.zip(strings, integers, Tuple::of).collect(Collectors.toList());
        //then
        assertThat(zipped)
                .extracting(Tuple.Tuple2::get1)
                .containsExactlyElementsOf(strings.subList(0, zipped.size()));
        assertThat(zipped)
                .extracting(Tuple.Tuple2::get2)
                .containsExactlyElementsOf(integers.subList(0, zipped.size()));
    }
}