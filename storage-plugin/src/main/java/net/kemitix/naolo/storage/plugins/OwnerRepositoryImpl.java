package net.kemitix.naolo.storage.plugins;

import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class OwnerRepositoryImpl
        extends AbstractEntityRepository<Owner>
        implements EntityRepository<Owner> {

    public OwnerRepositoryImpl(final EntityManager entityManager) {
        super(entityManager);
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
