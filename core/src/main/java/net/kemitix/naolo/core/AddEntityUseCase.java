package net.kemitix.naolo.core;

public interface AddEntityUseCase<T>
        extends EntityUseCase<T, AddEntityRequest<T>, AddEntityResponse<T>> {

    @Override
    default AddEntityResponse<T> invoke(final AddEntityRequest<T> request) {
        return () -> getRepository().add(request.getEntity());
    }

}
