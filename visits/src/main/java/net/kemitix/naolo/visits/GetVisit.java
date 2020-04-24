package net.kemitix.naolo.visits;

import lombok.Getter;
import net.kemitix.naolo.storage.EntityRepository;
import net.kemitix.naolo.storage.GetEntityRequest;
import net.kemitix.naolo.storage.GetEntityUseCase;

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
