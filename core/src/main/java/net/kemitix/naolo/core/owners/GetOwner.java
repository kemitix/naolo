package net.kemitix.naolo.core.owners;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.GetEntityRequest;
import net.kemitix.naolo.core.GetEntityUseCase;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
@RequiredArgsConstructor
public class GetOwner
        implements GetEntityUseCase<Owner> {

    @Getter
    private final EntityRepository<Owner> repository;

    @Override
    public GetEntityRequest<Owner> request(final long id) {
        return GetEntityRequest.create(id);
    }
}
