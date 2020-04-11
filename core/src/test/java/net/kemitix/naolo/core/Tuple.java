package net.kemitix.naolo.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Tuple<A, B> {

    private final A partA;
    private final B partB;

    public static <A, B> Tuple<A, B> of(final A a, final B b) {
        return new Tuple<>(a, b);
    }

    public A get1() {
        return partA;
    }

    public B get2() {
        return partB;
    }
}
