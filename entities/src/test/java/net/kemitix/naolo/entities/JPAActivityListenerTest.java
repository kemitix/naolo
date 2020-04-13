package net.kemitix.naolo.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JPAActivityListenerTest {

    private final JPAActivityListener listener = new JPAActivityListener();
    private final Owner owner = new Owner();

    @Test
    @DisplayName("@PrePersist")
    public void prePersist() {
        listener.prePersist(owner);
    }

    @Test
    @DisplayName("@PostPersist")
    public void postPersist() {
        listener.postPersist(owner);
    }

    @Test
    @DisplayName("@PreUpdate")
    public void preUpdate() {
        listener.preUpdate(owner);
    }

    @Test
    @DisplayName("@PostUpdate")
    public void postUpdate() {
        listener.postUpdate(owner);
    }

    @Test
    @DisplayName("@PreRemove")
    public void preRemove() {
        listener.preRemove(owner);
    }

    @Test
    @DisplayName("@PostRemove")
    public void postRemove() {
        listener.postRemove(owner);
    }

    @Test
    @DisplayName("@PostLoad")
    public void postLoad() {
        listener.postLoad(owner);
    }
}