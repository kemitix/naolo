package net.kemitix.naolo.core.jpa;

import java.util.List;

@FunctionalInterface
public interface ListEntityResponse<T>
        extends EntityUseCaseResponse<T> {
    List<T> getEntities();
}
