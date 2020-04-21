package net.kemitix.naolo.core.owners;

import lombok.Getter;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.AddEntityRequest;
import net.kemitix.naolo.storage.spi.AddEntityUseCase;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddOwner
        implements AddEntityUseCase<Owner> {

    @Getter
    private final EntityRepository<Owner> repository;

    @Inject
    public AddOwner(final EntityRepository<Owner> repository) {
        this.repository = repository;
    }

    @Override
    public AddEntityRequest<Owner> request(final Owner owner) {
        return AddEntityRequest.create(owner);
    }
}
