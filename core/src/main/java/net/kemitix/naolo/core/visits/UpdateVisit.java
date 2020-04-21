package net.kemitix.naolo.core.visits;

import lombok.Getter;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;
import net.kemitix.naolo.storage.spi.UpdateEntityRequest;
import net.kemitix.naolo.storage.spi.UpdateEntityUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UpdateVisit
        implements UpdateEntityUseCase<Visit> {

    @Getter
    private final EntityRepository<Visit> repository;

    @Inject
    public UpdateVisit(final EntityRepository<Visit> repository) {
        this.repository = repository;
    }

    @Override
    public UpdateEntityRequest<Visit> request(final Visit entity) {
        return UpdateEntityRequest.create(entity);
    }

}
