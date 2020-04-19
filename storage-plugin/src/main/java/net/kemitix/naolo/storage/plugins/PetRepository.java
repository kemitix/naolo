package net.kemitix.naolo.storage.plugins;

import net.kemitix.naolo.entities.Pet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class PetRepository
        extends AbstractEntityRepository<Pet>{

    @Inject
    public PetRepository(final EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Stream<Pet> findAll() {
        return findAll(Pet.FIND_ALL, Pet.class);
    }

    @Override
    public Optional<Pet> find(final long id) {
        return find(id, Pet.class);
    }

    @Override
    public Optional<Pet> update(final Pet entity) {
        return update(entity, Pet.class);
    }
}
