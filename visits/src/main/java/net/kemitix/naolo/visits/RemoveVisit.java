package net.kemitix.naolo.visits;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.EntityRepository;
import net.kemitix.naolo.core.jpa.RemoveEntityRequest;
import net.kemitix.naolo.core.jpa.RemoveEntityUseCase;

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
