package net.kemitix.naolo.core;

import net.kemitix.naolo.storage.spi.EntityRepository;

public interface EntityUseCase<
        T,
        REQ extends EntityUseCaseRequest<T>,
        RES extends EntityUseCaseResponse<T>>
        extends UseCase<REQ, RES> {
    EntityRepository<T> getRepository();
}
