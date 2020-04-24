package net.kemitix.naolo.visits;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.AddEntityRequest;
import net.kemitix.naolo.core.jpa.AddEntityUseCase;
import net.kemitix.naolo.core.jpa.EntityRepository;

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
