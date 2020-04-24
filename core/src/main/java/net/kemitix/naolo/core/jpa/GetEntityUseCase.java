package net.kemitix.naolo.core.jpa;

public interface GetEntityUseCase<T extends HasId>
        extends EntityUseCase<T, GetEntityRequest<T>, GetEntityResponse<T>> {

    @Override
    default GetEntityResponse<T> invoke(
            final GetEntityRequest<T> request
    ) {
        return () -> getRepository().find(request.getId());
    }

    GetEntityRequest<T> request(long id);
}
