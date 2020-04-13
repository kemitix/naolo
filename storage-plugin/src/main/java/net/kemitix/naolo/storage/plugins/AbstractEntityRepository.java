package net.kemitix.naolo.storage.plugins;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.entities.HasId;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Stream;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public abstract class AbstractEntityRepository<T>
        implements EntityRepository<T> {

    private final EntityManager entityManager;

    @Transactional
    @Override
    public T add(final T entity) {
        return entityManager.merge(entity);
    }

    public Optional<T> find(final long id, final Class<T> tClass) {
        return Optional.ofNullable(entityManager.find(tClass, id));
    }

    public Stream<T> findAll(
            final String namedQuery,
            final Class<T> tClass
    ) {
        return entityManager
                .createNamedQuery(namedQuery, tClass)
                .getResultStream();
    }

    @Transactional
    @Override
    public Optional<T> remove(final long id) {
        return find(id).map(entity -> {
            entityManager.remove(entity);
            return entity;
        });
    }

    @Transactional
    public <T extends HasId> Optional<T> update(
            final T entity,
            final Class<T> tClass
    ) {
        return Optional.ofNullable(
                entityManager.find(tClass, entity.getId()))
                .map(m -> entityManager.merge(entity));
    }
}