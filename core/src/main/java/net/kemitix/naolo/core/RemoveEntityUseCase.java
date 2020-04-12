package net.kemitix.naolo.core;

import net.kemitix.naolo.entities.HasId;

public interface RemoveEntityUseCase<T extends HasId>
        extends EntityUseCase<T, RemoveEntityRequest<T>, RemoveEntityResponse<T>> {

    @Override
    default RemoveEntityResponse<T> invoke(final RemoveEntityRequest<T> request) {
        return () -> getRepository().remove(request.getId());
    }

}
