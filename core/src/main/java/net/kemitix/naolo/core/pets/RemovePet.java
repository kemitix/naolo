package net.kemitix.naolo.core.pets;

import lombok.Getter;
import net.kemitix.naolo.core.RemoveEntityRequest;
import net.kemitix.naolo.core.RemoveEntityUseCase;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.storage.spi.EntityRepository;

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
