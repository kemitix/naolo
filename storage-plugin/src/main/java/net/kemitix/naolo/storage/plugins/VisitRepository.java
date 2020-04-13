package net.kemitix.naolo.storage.plugins;

import net.kemitix.naolo.entities.Visit;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class VisitRepository
        extends AbstractEntityRepository<Visit> {

    public VisitRepository(final EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Stream<Visit> findAll() {
        return findAll(Visit.FIND_ALL, Visit.class);
    }

    @Override
    public Optional<Visit> find(final long id) {
        return find(id, Visit.class);
    }

    @Override
    public Optional<Visit> update(final Visit entity) {
        return update(entity, Visit.class);
    }
}
