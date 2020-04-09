package net.kemitix.naolo.storage.plugins;

import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Stream;

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
        return veterinarian.withId(id);
    }

    @Override
    public Optional<Veterinarian> find(final long id) {
        return Optional.ofNullable(
                entityManager.find(Veterinarian.class, id));
    }

}
