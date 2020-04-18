package net.kemitix.naolo.storage.plugins;

import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;
import net.kemitix.naolo.storage.spi.StorageSpi;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class StorageSpiImpl
        implements StorageSpi {

    private final EntityManager entityManager;

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
