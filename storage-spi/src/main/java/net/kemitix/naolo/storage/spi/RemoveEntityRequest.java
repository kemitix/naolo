package net.kemitix.naolo.storage.spi;

import lombok.Getter;

public class RemoveEntityRequest<T>
        implements SingleEntityRequest<T> {

    @Getter
    private final long id;

    public RemoveEntityRequest(final long id) {
        this.id = id;
    }

    public static <T> RemoveEntityRequest<T> create(final long id) {
        return new RemoveEntityRequest<>(id);
    }
}
