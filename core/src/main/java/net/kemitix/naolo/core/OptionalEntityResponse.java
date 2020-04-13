package net.kemitix.naolo.core;

import java.util.Optional;

@FunctionalInterface
public interface OptionalEntityResponse<T>
        extends EntityUseCaseResponse<T> {
    Optional<T> getEntity();
}
