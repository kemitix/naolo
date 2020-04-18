package net.kemitix.naolo.storage.plugins;

import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class VeterinarianRepository
        extends AbstractEntityRepository<Veterinarian>
        implements EntityRepository<Veterinarian> {

    public VeterinarianRepository(final EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Stream<Veterinarian> findAll() {
        return findAll(Veterinarian.FIND_ALL, Veterinarian.class);
    }

    @Override
    public Optional<Veterinarian> find(final long id) {
        return find(id, Veterinarian.class);
    }

    @Override
    public Optional<Veterinarian> update(final Veterinarian entity) {
        return update(entity, Veterinarian.class);
    }
}
