package net.kemitix.naolo.core.owners;

import lombok.Getter;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.EntityRepository;
import net.kemitix.naolo.storage.GetEntityRequest;
import net.kemitix.naolo.storage.GetEntityUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GetOwner
        implements GetEntityUseCase<Owner> {

    @Getter
    private final EntityRepository<Owner> repository;

    @Inject
    public GetOwner(final EntityRepository<Owner> repository) {
        this.repository = repository;
    }

    @Override
    public GetEntityRequest<Owner> request(final long id) {
        return GetEntityRequest.create(id);
    }
}
