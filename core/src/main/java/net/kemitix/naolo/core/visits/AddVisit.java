package net.kemitix.naolo.core.visits;

import lombok.Getter;
import net.kemitix.naolo.core.AddEntityRequest;
import net.kemitix.naolo.core.AddEntityUseCase;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddVisit
        implements AddEntityUseCase<Visit> {

    @Getter
    private final EntityRepository<Visit> repository;

    @Inject
    public AddVisit(final EntityRepository<Visit> repository) {
        this.repository = repository;
    }

    @Override
    public AddEntityRequest<Visit> request(final Visit visit) {
        return AddEntityRequest.create(visit);
    }

}
