package net.kemitix.naolo.storage.spi;

import lombok.Getter;

public class GetEntityRequest<T>
        implements SingleEntityRequest<T> {

    @Getter
    private final long id;

    public GetEntityRequest(final long id) {
        this.id = id;
    }

    public static <T> GetEntityRequest<T> create(final long id) {
        return new GetEntityRequest<>(id);
    }
}
