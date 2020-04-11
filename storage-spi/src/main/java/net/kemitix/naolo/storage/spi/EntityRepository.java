package net.kemitix.naolo.storage.spi;

public interface EntityRepository<T> {
    T add(T entity);
}
