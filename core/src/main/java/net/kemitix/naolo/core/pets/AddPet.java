package net.kemitix.naolo.core.pets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.AddEntityRequest;
import net.kemitix.naolo.core.AddEntityUseCase;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
@RequiredArgsConstructor
public class AddPet
        implements AddEntityUseCase<Pet> {

    @Getter
    private final EntityRepository<Pet> repository;

    public static AddEntityRequest<Pet> request(final Pet pet) {
        return AddEntityRequest.create(pet);
    }
}
