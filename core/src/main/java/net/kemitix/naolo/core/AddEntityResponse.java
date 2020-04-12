package net.kemitix.naolo.core;

@FunctionalInterface
public interface AddEntityResponse<T>
        extends EntityUseCaseResponse<T> {
    T getEntity();
}
