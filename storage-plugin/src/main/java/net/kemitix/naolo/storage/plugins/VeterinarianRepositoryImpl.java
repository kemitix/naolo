package net.kemitix.naolo.storage.plugins;

import lombok.extern.java.Log;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.stream.Stream;

@Log
@ApplicationScoped
public class VeterinarianRepositoryImpl
        extends AbstractEntityRepository<Veterinarian>
        implements EntityRepository<Veterinarian> {

    public VeterinarianRepositoryImpl(final EntityManager entityManager) {
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
