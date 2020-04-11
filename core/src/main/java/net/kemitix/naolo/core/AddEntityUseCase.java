package net.kemitix.naolo.core;

import net.kemitix.naolo.storage.spi.EntityRepository;

public interface AddEntityUseCase<T>
        extends UseCase<AddEntityRequest<T>, AddEntityResponse<T>> {

    EntityRepository<T> getRepository();

    @Override
    default AddEntityResponse<T> invoke(final AddEntityRequest<T> request) {
        return () -> getRepository().add(request.getEntity());
    }

}
