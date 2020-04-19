package net.kemitix.naolo.core.pets;

import lombok.Getter;
import net.kemitix.naolo.core.AddEntityRequest;
import net.kemitix.naolo.core.AddEntityUseCase;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddPet
        implements AddEntityUseCase<Pet> {

    @Getter
    private final EntityRepository<Pet> repository;

    @Inject
    public AddPet(final EntityRepository<Pet> repository) {
        this.repository = repository;
    }

    @Override
    public AddEntityRequest<Pet> request(final Pet pet) {
        return AddEntityRequest.create(pet);
    }
}
