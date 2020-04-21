package net.kemitix.naolo.core.visits;

import lombok.Getter;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;
import net.kemitix.naolo.storage.spi.RemoveEntityRequest;
import net.kemitix.naolo.storage.spi.RemoveEntityUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RemoveVisit
        implements RemoveEntityUseCase<Visit> {

    @Getter
    private final EntityRepository<Visit> repository;

    @Inject
    public RemoveVisit(final EntityRepository<Visit> repository) {
        this.repository = repository;
    }

    @Override
    public RemoveEntityRequest<Visit> request(final long id) {
        return RemoveEntityRequest.create(id);
    }

}
