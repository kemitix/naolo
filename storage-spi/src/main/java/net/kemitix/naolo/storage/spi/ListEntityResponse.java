package net.kemitix.naolo.storage.spi;

import java.util.List;

@FunctionalInterface
public interface ListEntityResponse<T>
        extends EntityUseCaseResponse<T> {
    List<T> getEntities();
}
