package net.kemitix.naolo.core.owners;

import lombok.Getter;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.EntityRepository;
import net.kemitix.naolo.storage.spi.UpdateEntityRequest;
import net.kemitix.naolo.storage.spi.UpdateEntityUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UpdateOwner
        implements UpdateEntityUseCase<Owner> {

    @Getter
    private final EntityRepository<Owner> repository;

    @Inject
    public UpdateOwner(final EntityRepository<Owner> repository) {
        this.repository = repository;
    }

    @Override
    public UpdateEntityRequest<Owner> request(final Owner owner) {
        return UpdateEntityRequest.create(owner);
    }
}
