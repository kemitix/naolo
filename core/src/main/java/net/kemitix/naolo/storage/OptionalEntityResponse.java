package net.kemitix.naolo.storage;

import java.util.Optional;

@FunctionalInterface
public interface OptionalEntityResponse<T>
        extends EntityUseCaseResponse<T> {
    Optional<T> getEntity();
}
