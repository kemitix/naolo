package net.kemitix.naolo.core.visits;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.RemoveEntityRequest;
import net.kemitix.naolo.core.RemoveEntityUseCase;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;

@Getter
@RequiredArgsConstructor
public class RemoveVisit
        implements RemoveEntityUseCase<Visit> {

    private final EntityRepository<Visit> repository;

    @Override
    public RemoveEntityRequest<Visit> request(final long id) {
        return RemoveEntityRequest.create(id);
    }

}
