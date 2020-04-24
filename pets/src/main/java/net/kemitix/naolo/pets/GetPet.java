package net.kemitix.naolo.pets;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.EntityRepository;
import net.kemitix.naolo.core.jpa.GetEntityRequest;
import net.kemitix.naolo.core.jpa.GetEntityUseCase;

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
