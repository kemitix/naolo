package net.kemitix.naolo.storage.plugins;

import lombok.extern.java.Log;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Stream;

@Log
@ApplicationScoped
public class VeterinarianRepositoryImpl
        implements VeterinarianRepository {

    private final EntityManager entityManager;

    public VeterinarianRepositoryImpl(
            final EntityManager entityManager
    ) {
        this.entityManager = entityManager;
    }

    @Override
    public Stream<Veterinarian> findAll() {
        log.info("find all");
        return entityManager
                .createNamedQuery(Veterinarian.FIND_ALL, Veterinarian.class)
                .getResultStream();
    }

    @Transactional
    @Override
    public Veterinarian add(final Veterinarian veterinarian) {
        final Veterinarian merged = entityManager.merge(veterinarian);
        entityManager.persist(merged);
        final Long id = merged.getId();
        log.info(String.format("added %d", id));
        return veterinarian.withId(id);
    }

    @Override
    public Optional<Veterinarian> find(final long id) {
        log.info(String.format("find %d", id));
        return Optional.ofNullable(
                entityManager.find(Veterinarian.class, id));
    }

    @Transactional
    @Override
    public Optional<Veterinarian> update(final Veterinarian veterinarian) {
        final Long id = veterinarian.getId();
        log.info(String.format("update %d", id));
        return Optional.ofNullable(
                entityManager.find(Veterinarian.class, id))
                .map(e -> entityManager.merge(veterinarian));
    }

    @Transactional
    @Override
    public Optional<Veterinarian> remove(final long id) {
        log.info(String.format("remove %d", id));
        return Optional.ofNullable(
                entityManager.find(Veterinarian.class, id))
                .map(e -> {
                    entityManager.remove(e);
                    return e;
                });
    }

}
