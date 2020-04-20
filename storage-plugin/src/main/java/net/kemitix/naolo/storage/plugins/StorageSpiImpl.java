package net.kemitix.naolo.storage.plugins;

import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;
import net.kemitix.naolo.storage.spi.StorageSpi;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class StorageSpiImpl
        implements StorageSpi {

    private final EntityManager entityManager;

    @Inject
    public StorageSpiImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public EntityRepository<Veterinarian> vetRepository() {
        return new VeterinarianRepository(entityManager);
    }

    @Override
    public EntityRepository<Owner> ownerRepository() {
        return new OwnerRepository(entityManager);
    }

    @Override
    public EntityRepository<Pet> petRepository() {
        return new PetRepository(entityManager);
    }

    @Override
    public EntityRepository<Visit> visitRepository() {
        return new VisitRepository(entityManager);
    }
}
