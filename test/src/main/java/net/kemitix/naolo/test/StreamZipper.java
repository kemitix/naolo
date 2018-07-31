/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Paul Campbell
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.kemitix.naolo.test;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import static java.lang.Math.min;
import static java.util.stream.IntStream.range;

/**
 * Utility to zip two {@link Stream}s together.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
public final class StreamZipper {

    private StreamZipper() {
        throw new UnsupportedOperationException();
    }

    /**
     * Zip two {@link Stream}s together.
     *
     * <p>The resulting stream will contain only as many items as the shortest of the two lists.</p>
     *
     * @param a the first List
     * @param b the second List
     * @param zipper the function to zip an item from each list
     * @param <A> the type of the first list
     * @param <B> the type of the second list
     * @param <C> the type of the joined items
     * @return a Stream of the joined items
     */
    public static <A, B, C> Stream<C> zip(
            final List<A> a,
            final List<B> b,
            final BiFunction<A, B, C> zipper
    ) {
        return range(0, limit(a, b))
                .mapToObj(tuple(a, b, zipper));
    }

    private static <A, B> int limit(
            final List<A> a,
            final List<B> b
    ) {
        return min(a.size(), b.size());
    }

    private static <A, B, C> IntFunction<C> tuple(
            final List<A> a,
            final List<B> b,
            final BiFunction<A, B, C> zipper
    ) {
        return i -> zipper.apply(a.get(i), b.get(i));
    }

}
