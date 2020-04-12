package net.kemitix.naolo.core;

import net.kemitix.naolo.entities.HasId;

public interface AddEntityUseCase<T extends HasId>
        extends EntityUseCase<T, AddEntityRequest<T>, AddEntityResponse<T>> {

    @Override
    default AddEntityResponse<T> invoke(final AddEntityRequest<T> request) {
        return () -> getRepository().add(request.getEntity());
    }

}
