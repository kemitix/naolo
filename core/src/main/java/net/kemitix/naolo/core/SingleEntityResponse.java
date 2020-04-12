package net.kemitix.naolo.core;

public interface SingleEntityResponse<T>
        extends EntityUseCaseResponse<T> {

    T getEntity();
}
