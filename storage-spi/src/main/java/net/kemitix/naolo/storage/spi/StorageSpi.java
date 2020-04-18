package net.kemitix.naolo.storage.spi;

import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.entities.Visit;

/**
 * Plugin implementing the storage-spi module should implement this interface.
 * <p>
 * This interface is not directly used, but is a single interface that plugin
 * developers can implement to verify they have implemented all interfaces
 * required.
 */
public interface StorageSpi {

    EntityRepository<Veterinarian> vetRepository();
    EntityRepository<Owner> ownerRepository();
    EntityRepository<Pet> petRepository();
    EntityRepository<Visit> visitRepository();

}
