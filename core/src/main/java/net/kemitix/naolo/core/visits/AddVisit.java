package net.kemitix.naolo.core.visits;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.AddEntityRequest;
import net.kemitix.naolo.core.AddEntityUseCase;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;

@Getter
@RequiredArgsConstructor
public class AddVisit
        implements AddEntityUseCase<Visit> {

    private final EntityRepository<Visit> repository;

    @Override
    public AddEntityRequest<Visit> request(final Visit visit) {
        return AddEntityRequest.create(visit);
    }

}
