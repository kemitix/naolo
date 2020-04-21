package net.kemitix.naolo.core.owners;

import lombok.Getter;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.EntityRepository;
import net.kemitix.naolo.storage.spi.ListEntityRequest;
import net.kemitix.naolo.storage.spi.ListEntityUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ListOwners
        implements ListEntityUseCase<Owner> {

    @Getter
    private final EntityRepository<Owner> repository;

    @Inject
    public ListOwners(final EntityRepository<Owner> repository) {
        this.repository = repository;
    }

    @Override
    public ListEntityRequest<Owner> request() {
        return ListEntityRequest.create();
    }
}
