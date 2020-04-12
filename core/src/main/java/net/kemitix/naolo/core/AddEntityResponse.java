package net.kemitix.naolo.core;

@FunctionalInterface
public interface AddEntityResponse<T> {
    T getEntity();
}
