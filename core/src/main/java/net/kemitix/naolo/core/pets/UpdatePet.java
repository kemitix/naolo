package net.kemitix.naolo.core.pets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.UpdateEntityRequest;
import net.kemitix.naolo.core.UpdateEntityUseCase;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
@RequiredArgsConstructor
public class UpdatePet
        implements UpdateEntityUseCase<Pet> {

    @Getter
    private final EntityRepository<Pet> repository;

    public static UpdateEntityRequest<Pet> request(final Pet pet) {
        return UpdateEntityRequest.create(pet);
    }
}
