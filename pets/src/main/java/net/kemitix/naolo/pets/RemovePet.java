package net.kemitix.naolo.pets;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.EntityRepository;
import net.kemitix.naolo.core.jpa.RemoveEntityRequest;
import net.kemitix.naolo.core.jpa.RemoveEntityUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RemovePet
        implements RemoveEntityUseCase<Pet> {

    @Getter
    private final EntityRepository<Pet> repository;

    @Inject
    public RemovePet(final EntityRepository<Pet> repository) {
        this.repository = repository;
    }

    @Override
    public RemoveEntityRequest<Pet> request(final long id) {
        return RemoveEntityRequest.create(id);
    }
}
