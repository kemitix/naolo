package net.kemitix.naolo.storage;

public interface EntityUseCase<
        T extends HasId,
        REQ extends EntityUseCaseRequest<T>,
        RES extends EntityUseCaseResponse<T>>
        extends UseCase<REQ, RES> {
    EntityRepository<T> getRepository();
}
