package net.kemitix.naolo.core;

public class Tuple<A, B> {

    private final A partA;
    private final B partB;

    private Tuple(final A partA, final B partB) {
        this.partA = partA;
        this.partB = partB;
    }

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
