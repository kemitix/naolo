package net.kemitix.naolo.core;

import net.kemitix.naolo.entities.HasId;

import java.util.stream.Collectors;

public interface ListEntityUseCase<T extends HasId>
        extends EntityUseCase<T, ListEntityRequest<T>, ListEntityResponse<T>> {

    @Override
    default ListEntityResponse<T> invoke(
            final ListEntityRequest<T> request
    ) {
        return () -> getRepository().findAll().collect(Collectors.toList());
    }
}
