package net.kemitix.naolo.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateEntityRequest<T>
        implements SingleEntityRequest<T> {

    @Getter
    private final T entity;

    public static <T> UpdateEntityRequest<T> create(final T entity) {
        return new UpdateEntityRequest<>(entity);
    }

}
