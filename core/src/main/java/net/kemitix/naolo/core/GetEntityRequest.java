package net.kemitix.naolo.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetEntityRequest<T>
        implements SingleEntityRequest<T> {

    @Getter
    private final long id;

}
