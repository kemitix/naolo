package net.kemitix.naolo.visits;

import net.kemitix.naolo.storage.AbstractEntityRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class VisitRepository
        extends AbstractEntityRepository<Visit> {

    @Inject
    public VisitRepository(final EntityManager entityManager) {
        super(entityManager);
    }

    protected VisitRepository() {
        super(null);
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
