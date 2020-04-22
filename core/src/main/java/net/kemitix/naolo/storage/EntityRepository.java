package net.kemitix.naolo.storage;

import java.util.Optional;
import java.util.stream.Stream;

public interface EntityRepository<T> {

    /**
     * Find all entities.
     *
     * @return a Stream of entities.
     */
    Stream<T> findAll();

    /**
     * Adds a new Veterinarian.
     *
     * @param veterinarian the vet to add
     * @return the added vet
     */
    T add(T veterinarian);

    /**
     * Finds an entity for the id.
     *
     * @param id the id of the entity to find
     * @return an Optional containing the entity if found, empty otherwise.
     */
    Optional<T> find(long id);

    /**
     * Updates the entity.
     *
     * @param entity the entity to update
     * @return an Optional containing the entity if it exists, empty otherwise.
     */
    Optional<T> update(T entity);

    /**
     * Removes the entity.
     *
     * @param id the id of the entity to remove
     * @return an Optional containing the entity if it existed, empty otherwise.
     */
    Optional<T> remove(long id);
}
