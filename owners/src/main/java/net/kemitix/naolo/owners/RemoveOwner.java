package net.kemitix.naolo.owners;

import lombok.Getter;
import net.kemitix.naolo.storage.EntityRepository;
import net.kemitix.naolo.storage.RemoveEntityRequest;
import net.kemitix.naolo.storage.RemoveEntityUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RemoveOwner
        implements RemoveEntityUseCase<Owner> {

    @Getter
    private final EntityRepository<Owner> repository;

    @Inject
    public RemoveOwner(final EntityRepository<Owner> repository) {
        this.repository = repository;
    }

    @Override
    public RemoveEntityRequest<Owner> request(final long id) {
        return RemoveEntityRequest.create(id);
    }

}
