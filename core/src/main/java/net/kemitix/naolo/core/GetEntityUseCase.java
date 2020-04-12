package net.kemitix.naolo.core;

import net.kemitix.naolo.entities.HasId;

public interface GetEntityUseCase<T extends HasId>
        extends EntityUseCase<T, GetEntityRequest<T>, GetEntityResponse<T>> {

    @Override
    default GetEntityResponse<T> invoke(
            final GetEntityRequest<T> request
    ) {
        return () -> getRepository().find(request.getId());
    }

}
