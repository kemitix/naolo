package net.kemitix.naolo.owners;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.EntityRepository;
import net.kemitix.naolo.core.jpa.RemoveEntityRequest;
import net.kemitix.naolo.core.jpa.RemoveEntityUseCase;

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
