package net.kemitix.naolo.core.owners;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.RemoveEntityRequest;
import net.kemitix.naolo.core.RemoveEntityUseCase;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
@RequiredArgsConstructor
public class RemoveOwner
        implements RemoveEntityUseCase<Owner> {

    @Getter
    private final EntityRepository<Owner> repository;

    public static RemoveEntityRequest<Owner> request(final long id) {
        return RemoveEntityRequest.create(id);
    }

}
