package net.kemitix.naolo.core.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JPAActivityListenerTest {

    private final JPAActivityListener listener = new JPAActivityListener();
    private final Object object = new Object();

    @Test
    @DisplayName("@PrePersist")
    public void prePersist() {
        listener.prePersist(object);
    }

    @Test
    @DisplayName("@PostPersist")
    public void postPersist() {
        listener.postPersist(object);
    }

    @Test
    @DisplayName("@PreUpdate")
    public void preUpdate() {
        listener.preUpdate(object);
    }

    @Test
    @DisplayName("@PostUpdate")
    public void postUpdate() {
        listener.postUpdate(object);
    }

    @Test
    @DisplayName("@PreRemove")
    public void preRemove() {
        listener.preRemove(object);
    }

    @Test
    @DisplayName("@PostRemove")
    public void postRemove() {
        listener.postRemove(object);
    }

    @Test
    @DisplayName("@PostLoad")
    public void postLoad() {
        listener.postLoad(object);
    }
}