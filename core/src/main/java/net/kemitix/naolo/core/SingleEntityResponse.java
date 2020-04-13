package net.kemitix.naolo.core;

@FunctionalInterface
public interface SingleEntityResponse<T>
        extends EntityUseCaseResponse<T> {
    T getEntity();
}
