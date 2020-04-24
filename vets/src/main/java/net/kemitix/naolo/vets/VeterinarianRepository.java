package net.kemitix.naolo.vets;

import net.kemitix.naolo.storage.AbstractEntityRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class VeterinarianRepository
        extends AbstractEntityRepository<Veterinarian> {

    @Inject
    public VeterinarianRepository(final EntityManager entityManager) {
        super(entityManager);
    }

    protected VeterinarianRepository() {
        super(null);
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
