package net.kemitix.naolo.core.visits;

import lombok.Getter;
import net.kemitix.naolo.core.GetEntityRequest;
import net.kemitix.naolo.core.GetEntityUseCase;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GetVisit
        implements GetEntityUseCase<Visit> {

    @Getter
    private final EntityRepository<Visit> repository;

    @Inject
    public GetVisit(final EntityRepository<Visit> repository) {
        this.repository = repository;
    }

    @Override
    public GetEntityRequest<Visit> request(final long id) {
        return GetEntityRequest.create(id);
    }

}
