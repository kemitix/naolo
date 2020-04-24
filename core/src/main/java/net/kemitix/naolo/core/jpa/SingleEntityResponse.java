package net.kemitix.naolo.core.jpa;

@FunctionalInterface
public interface SingleEntityResponse<T>
        extends EntityUseCaseResponse<T> {
    T getEntity();
}
