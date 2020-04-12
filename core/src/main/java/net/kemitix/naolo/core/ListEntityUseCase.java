package net.kemitix.naolo.core;

import net.kemitix.naolo.storage.spi.EntityRepository;

import java.util.stream.Collectors;

public interface ListEntityUseCase<T>
        extends UseCase<ListEntityRequest<T>, ListEntityResponse<T>> {

    EntityRepository<T> getRepository();

    @Override
    default ListEntityResponse<T> invoke(
            final ListEntityRequest<T> request
    ) {
        return () -> getRepository().findAll().collect(Collectors.toList());
    }
}
