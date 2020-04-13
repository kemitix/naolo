package net.kemitix.naolo.core.owners;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.UpdateEntityRequest;
import net.kemitix.naolo.core.UpdateEntityUseCase;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
@RequiredArgsConstructor
public class UpdateOwner
        implements UpdateEntityUseCase<Owner> {

    @Getter
    private final EntityRepository<Owner> repository;

    @Override
    public UpdateEntityRequest<Owner> request(final Owner owner) {
        return UpdateEntityRequest.create(owner);
    }
}
