package net.kemitix.naolo.core;

import net.kemitix.naolo.entities.HasId;

import java.util.Optional;

public interface RemoveEntityUseCase<T extends HasId>
        extends EntityUseCase<T, RemoveEntityRequest<T>, RemoveEntityResponse<T>> {

    @Override
    default RemoveEntityResponse<T> invoke(final RemoveEntityRequest<T> request) {
        final Optional<T> removed = getRepository().remove(request.getId());
        return () -> removed;
    }

}
