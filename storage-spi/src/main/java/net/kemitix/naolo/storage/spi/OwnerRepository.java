package net.kemitix.naolo.storage.spi;

import net.kemitix.naolo.entities.Owner;

import java.util.Optional;
import java.util.stream.Stream;

public interface OwnerRepository {
    Owner add(Owner owner);

    Optional<Owner> find(long id);

    Stream<Owner> findAll();

    Optional<Owner> remove(long id);

    Optional<Owner> update(Owner owner);
}
