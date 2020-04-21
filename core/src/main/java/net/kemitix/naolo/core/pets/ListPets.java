package net.kemitix.naolo.core.pets;

import lombok.Getter;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.storage.spi.EntityRepository;
import net.kemitix.naolo.storage.spi.ListEntityRequest;
import net.kemitix.naolo.storage.spi.ListEntityUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ListPets
        implements ListEntityUseCase<Pet> {

    @Getter
    private final EntityRepository<Pet> repository;

    @Inject
    public ListPets(final EntityRepository<Pet> repository) {
        this.repository = repository;
    }

    @Override
    public ListEntityRequest<Pet> request() {
        return ListEntityRequest.create();
    }

}
