package net.kemitix.naolo.storage;

public interface AddEntityUseCase<T extends HasId>
        extends EntityUseCase<T, AddEntityRequest<T>, AddEntityResponse<T>> {

    @Override
    default AddEntityResponse<T> invoke(final AddEntityRequest<T> request) {
        final T added = getRepository().add(request.getEntity());
        return () -> added;
    }

    AddEntityRequest<T> request(T entity);
}
