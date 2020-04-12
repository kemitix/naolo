package net.kemitix.naolo.core.pets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.RemoveEntityRequest;
import net.kemitix.naolo.core.RemoveEntityUseCase;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
@RequiredArgsConstructor
public class RemovePet
        implements RemoveEntityUseCase<Pet> {

    @Getter
    private final EntityRepository<Pet> repository;

    public static RemoveEntityRequest<Pet> request(final long id) {
        return RemoveEntityRequest.create(id);
    }
}
