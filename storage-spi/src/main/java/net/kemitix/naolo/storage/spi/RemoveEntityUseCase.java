package net.kemitix.naolo.storage.spi;

import java.util.Optional;

public interface RemoveEntityUseCase<T extends HasId>
        extends EntityUseCase<T, RemoveEntityRequest<T>, RemoveEntityResponse<T>> {

    @Override
    default RemoveEntityResponse<T> invoke(final RemoveEntityRequest<T> request) {
        final Optional<T> removed = getRepository().remove(request.getId());
        return () -> removed;
    }

    RemoveEntityRequest<T> request(long id);
}
