package net.kemitix.naolo.owners;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.EntityRepository;
import net.kemitix.naolo.core.jpa.GetEntityRequest;
import net.kemitix.naolo.core.jpa.GetEntityUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GetOwner
        implements GetEntityUseCase<Owner> {

    @Getter
    private final EntityRepository<Owner> repository;

    @Inject
    public GetOwner(final EntityRepository<Owner> repository) {
        this.repository = repository;
    }

    @Override
    public GetEntityRequest<Owner> request(final long id) {
        return GetEntityRequest.create(id);
    }
}
