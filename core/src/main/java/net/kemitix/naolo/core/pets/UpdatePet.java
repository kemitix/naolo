package net.kemitix.naolo.core.pets;

import lombok.Getter;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.storage.spi.EntityRepository;
import net.kemitix.naolo.storage.spi.UpdateEntityRequest;
import net.kemitix.naolo.storage.spi.UpdateEntityUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UpdatePet
        implements UpdateEntityUseCase<Pet> {

    @Getter
    private final EntityRepository<Pet> repository;

    @Inject
    public UpdatePet(final EntityRepository<Pet> repository) {
        this.repository = repository;
    }

    @Override
    public UpdateEntityRequest<Pet> request(final Pet pet) {
        return UpdateEntityRequest.create(pet);
    }
}
