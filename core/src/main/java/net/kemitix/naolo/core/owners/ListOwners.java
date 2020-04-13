package net.kemitix.naolo.core.owners;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.ListEntityRequest;
import net.kemitix.naolo.core.ListEntityUseCase;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class ListOwners
        implements ListEntityUseCase<Owner> {

    @Getter
    private final EntityRepository<Owner> repository;

    @Override
    public ListEntityRequest<Owner> request() {
        return ListEntityRequest.create();
    }
}
