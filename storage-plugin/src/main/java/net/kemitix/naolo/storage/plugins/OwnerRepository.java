package net.kemitix.naolo.storage.plugins;

import net.kemitix.naolo.entities.Owner;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class OwnerRepository
        extends AbstractEntityRepository<Owner> {

    @Inject
    public OwnerRepository(final EntityManager entityManager) {
        super(entityManager);
    }

    protected OwnerRepository() {
        super(null);
    }

    @Override
    public Stream<Owner> findAll() {
        return findAll(Owner.FIND_ALL, Owner.class);
    }

    @Override
    public Optional<Owner> find(final long id) {
        return find(id, Owner.class);
    }

    @Override
    public Optional<Owner> update(final Owner entity) {
        return update(entity, Owner.class);
    }
}
