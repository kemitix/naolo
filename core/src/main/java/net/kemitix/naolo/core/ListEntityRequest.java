package net.kemitix.naolo.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ListEntityRequest<T>
        implements EntityUseCaseRequest<T> {

    public static final ListEntityRequest<?> REQUEST =
            new ListEntityRequest<>();

    @SuppressWarnings("unchecked")
    public static <T> ListEntityRequest<T> create() {
        return (ListEntityRequest<T>) REQUEST;
    }

}
