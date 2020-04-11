package net.kemitix.naolo.storage.plugins;

import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.OwnerRepository;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Stream;

@Dependent
@RequiredArgsConstructor
public class OwnerRepositoryImpl implements OwnerRepository {
    private final EntityManager entityManager;

    @Transactional
    @Override
    public Owner add(final Owner owner) {
        return entityManager.merge(owner);
    }

    @Override
    public Optional<Owner> find(final long id) {
        return Optional.ofNullable(entityManager.find(Owner.class, id));
    }

    @Override
    public Stream<Owner> findAll() {
        return entityManager
                .createNamedQuery(Owner.FIND_ALL, Owner.class)
                .getResultStream();
    }

    @Transactional
    @Override
    public Optional<Owner> remove(final long id) {
        return find(id).map(owner -> {
            entityManager.remove(owner);
            return owner;
        });
    }

    @Transactional
    @Override
    public Optional<Owner> update(final Owner owner) {
        return Optional.ofNullable(
                entityManager.find(Owner.class, owner.getId()))
                .map(m -> entityManager.merge(owner));

    }
}
