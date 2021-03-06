package net.kemitix.naolo.pets;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.AddEntityRequest;
import net.kemitix.naolo.core.jpa.AddEntityUseCase;
import net.kemitix.naolo.core.jpa.EntityRepository;

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
