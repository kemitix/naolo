package net.kemitix.naolo.core;

import net.kemitix.naolo.entities.HasId;

public interface UpdateEntityUseCase<T extends HasId>
        extends EntityUseCase<T, UpdateEntityRequest<T>, UpdateEntityResponse<T>> {

    @Override
    default UpdateEntityResponse<T> invoke(final UpdateEntityRequest<T> request) {
        return () -> getRepository().update(request.getEntity());
    }
}
