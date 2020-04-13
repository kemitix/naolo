package net.kemitix.naolo.core.visits;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.GetEntityRequest;
import net.kemitix.naolo.core.GetEntityUseCase;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;

@Getter
@RequiredArgsConstructor
public class GetVisit
        implements GetEntityUseCase<Visit> {

    private final EntityRepository<Visit> repository;

    @Override
    public GetEntityRequest<Visit> request(final long id) {
        return GetEntityRequest.create(id);
    }

}
