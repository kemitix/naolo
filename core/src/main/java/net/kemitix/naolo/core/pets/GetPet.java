package net.kemitix.naolo.core.pets;

import lombok.Getter;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.storage.EntityRepository;
import net.kemitix.naolo.storage.GetEntityRequest;
import net.kemitix.naolo.storage.GetEntityUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GetPet
        implements GetEntityUseCase<Pet> {

    @Getter
    private final EntityRepository<Pet> repository;

    @Inject
    public GetPet(final EntityRepository<Pet> repository) {
        this.repository = repository;
    }

    @Override
    public GetEntityRequest<Pet> request(final long id) {
        return GetEntityRequest.create(id);
    }

}
