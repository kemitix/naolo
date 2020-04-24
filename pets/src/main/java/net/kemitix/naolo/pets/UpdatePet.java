package net.kemitix.naolo.pets;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.EntityRepository;
import net.kemitix.naolo.core.jpa.UpdateEntityRequest;
import net.kemitix.naolo.core.jpa.UpdateEntityUseCase;

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
