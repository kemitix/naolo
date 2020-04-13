package net.kemitix.naolo.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddEntityRequest<T>
        implements SingleEntityRequest<T> {

    private final T entity;

    public static <T> AddEntityRequest<T> create(final T entity) {
        return new AddEntityRequest<>(entity);
    }

}