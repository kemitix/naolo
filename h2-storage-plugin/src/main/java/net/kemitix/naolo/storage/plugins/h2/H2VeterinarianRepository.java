package net.kemitix.naolo.storage.plugins.h2;

import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.stream.Stream;

@ApplicationScoped
public class H2VeterinarianRepository
        implements VeterinarianRepository {

    private final EntityManager entityManager;

    public H2VeterinarianRepository(
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
        final Long id = merged.getId();
        return veterinarian.withId(id);
    }

}
