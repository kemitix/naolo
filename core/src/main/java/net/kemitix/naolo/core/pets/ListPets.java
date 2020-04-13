package net.kemitix.naolo.core.pets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.ListEntityRequest;
import net.kemitix.naolo.core.ListEntityUseCase;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class ListPets
        implements ListEntityUseCase<Pet> {

    @Getter
    private final EntityRepository<Pet> repository;

    @Override
    public ListEntityRequest<Pet> request() {
        return ListEntityRequest.create();
    }

}
