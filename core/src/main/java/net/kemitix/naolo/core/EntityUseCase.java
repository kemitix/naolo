package net.kemitix.naolo.core;

import net.kemitix.naolo.entities.HasId;
import net.kemitix.naolo.storage.spi.EntityRepository;

public interface EntityUseCase<
        T extends HasId,
        REQ extends EntityUseCaseRequest<T>,
        RES extends EntityUseCaseResponse<T>>
        extends UseCase<REQ, RES> {
    EntityRepository<T> getRepository();
}
