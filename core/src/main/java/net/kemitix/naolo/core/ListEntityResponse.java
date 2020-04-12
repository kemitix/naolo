package net.kemitix.naolo.core;

import java.util.List;

@FunctionalInterface
public interface ListEntityResponse<T> {
    List<T> getEntities();
}
