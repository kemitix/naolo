package net.kemitix.naolo.core.pets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.GetEntityRequest;
import net.kemitix.naolo.core.GetEntityUseCase;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class GetPet
        implements GetEntityUseCase<Pet> {

    @Getter
    private final EntityRepository<Pet> repository;

    @Override
    public GetEntityRequest<Pet> request(final long id) {
        return GetEntityRequest.create(id);
    }

}
