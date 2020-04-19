package net.kemitix.naolo.core.visits;

import lombok.Getter;
import net.kemitix.naolo.core.RemoveEntityRequest;
import net.kemitix.naolo.core.RemoveEntityUseCase;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;

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
