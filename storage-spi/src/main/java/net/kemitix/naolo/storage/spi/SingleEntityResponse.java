package net.kemitix.naolo.storage.spi;

@FunctionalInterface
public interface SingleEntityResponse<T>
        extends EntityUseCaseResponse<T> {
    T getEntity();
}
