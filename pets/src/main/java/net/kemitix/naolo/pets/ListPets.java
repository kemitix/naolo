package net.kemitix.naolo.pets;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.EntityRepository;
import net.kemitix.naolo.core.jpa.ListEntityRequest;
import net.kemitix.naolo.core.jpa.ListEntityUseCase;

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
