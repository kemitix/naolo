package net.kemitix.naolo.storage;

@FunctionalInterface
public interface SingleEntityResponse<T>
        extends EntityUseCaseResponse<T> {
    T getEntity();
}
