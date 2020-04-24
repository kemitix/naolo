package net.kemitix.naolo.visits;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.EntityRepository;
import net.kemitix.naolo.core.jpa.UpdateEntityRequest;
import net.kemitix.naolo.core.jpa.UpdateEntityUseCase;

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
