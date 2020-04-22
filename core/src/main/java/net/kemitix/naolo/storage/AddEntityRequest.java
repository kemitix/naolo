package net.kemitix.naolo.storage;

import lombok.Getter;

public class AddEntityRequest<T>
        implements SingleEntityRequest<T> {

    @Getter
    private final T entity;

    public AddEntityRequest(final T entity) {
        this.entity = entity;
    }

    public static <T> AddEntityRequest<T> create(final T entity) {
        return new AddEntityRequest<>(entity);
    }

}
