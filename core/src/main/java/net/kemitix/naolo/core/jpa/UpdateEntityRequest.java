package net.kemitix.naolo.core.jpa;

import lombok.Getter;

public class UpdateEntityRequest<T>
        implements SingleEntityRequest<T> {

    @Getter
    private final T entity;

    public UpdateEntityRequest(final T entity) {
        this.entity = entity;
    }

    public static <T> UpdateEntityRequest<T> create(final T entity) {
        return new UpdateEntityRequest<>(entity);
    }

}
