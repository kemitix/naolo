package net.kemitix.naolo.core;

import java.util.stream.Collectors;

public interface ListEntityUseCase<T>
        extends EntityUseCase<T, ListEntityRequest<T>, ListEntityResponse<T>> {

    @Override
    default ListEntityResponse<T> invoke(
            final ListEntityRequest<T> request
    ) {
        return () -> getRepository().findAll().collect(Collectors.toList());
    }
}
