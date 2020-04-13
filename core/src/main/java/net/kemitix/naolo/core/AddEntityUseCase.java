package net.kemitix.naolo.core;

import net.kemitix.naolo.entities.HasId;

public interface AddEntityUseCase<T extends HasId>
        extends EntityUseCase<T, AddEntityRequest<T>, AddEntityResponse<T>> {

    @Override
    default AddEntityResponse<T> invoke(final AddEntityRequest<T> request) {
        final T added = getRepository().add(request.getEntity());
        return () -> added;
    }

    AddEntityRequest<T> request(T entity);
}
