package net.kemitix.naolo.core.owners;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.AddEntityRequest;
import net.kemitix.naolo.core.AddEntityUseCase;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
@RequiredArgsConstructor
public class AddOwner
        implements AddEntityUseCase<Owner> {

    @Getter
    private final EntityRepository<Owner> repository;

    @Override
    public AddEntityRequest<Owner> request(final Owner owner) {
        return AddEntityRequest.create(owner);
    }
}
