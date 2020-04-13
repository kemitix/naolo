package net.kemitix.naolo.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RemoveEntityRequest<T>
        implements SingleEntityRequest<T> {

    @Getter
    private final long id;

    public static <T> RemoveEntityRequest<T> create(final long id) {
        return new RemoveEntityRequest<>(id);
    }
}
