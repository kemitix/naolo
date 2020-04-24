package net.kemitix.naolo.owners;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.EntityRepository;
import net.kemitix.naolo.core.jpa.UpdateEntityRequest;
import net.kemitix.naolo.core.jpa.UpdateEntityUseCase;

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
