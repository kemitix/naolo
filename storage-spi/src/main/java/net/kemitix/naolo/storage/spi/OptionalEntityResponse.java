package net.kemitix.naolo.storage.spi;

import java.util.Optional;

@FunctionalInterface
public interface OptionalEntityResponse<T>
        extends EntityUseCaseResponse<T> {
    Optional<T> getEntity();
}
