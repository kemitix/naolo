package net.kemitix.naolo.core.owners;

import lombok.Getter;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.EntityRepository;
import net.kemitix.naolo.storage.spi.RemoveEntityRequest;
import net.kemitix.naolo.storage.spi.RemoveEntityUseCase;

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
