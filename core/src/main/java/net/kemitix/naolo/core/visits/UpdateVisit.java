package net.kemitix.naolo.core.visits;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.UpdateEntityRequest;
import net.kemitix.naolo.core.UpdateEntityUseCase;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;

@Getter
@RequiredArgsConstructor
public class UpdateVisit
        implements UpdateEntityUseCase<Visit> {

    private final EntityRepository<Visit> repository;

    @Override
    public UpdateEntityRequest<Visit> request(final Visit entity) {
        return UpdateEntityRequest.create(entity);
    }

}
