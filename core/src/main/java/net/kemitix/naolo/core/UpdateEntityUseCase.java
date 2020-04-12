package net.kemitix.naolo.core;

import net.kemitix.naolo.entities.HasId;

import java.util.Optional;

public interface UpdateEntityUseCase<T extends HasId>
        extends EntityUseCase<T, UpdateEntityRequest<T>, UpdateEntityResponse<T>> {

    @Override
    default UpdateEntityResponse<T> invoke(final UpdateEntityRequest<T> request) {
        final Optional<T> updated = getRepository().update(request.getEntity());
        return () -> updated;
    }
}
