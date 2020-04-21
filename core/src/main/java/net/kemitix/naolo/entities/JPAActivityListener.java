package net.kemitix.naolo.entities;

import lombok.extern.java.Log;

import javax.persistence.*;

/**
 * Simple Activity Listener for any JPA Entity.
 * <p>
 * <strong>Do not doing anything like this in a productions system! Logging
 * records with their default {@link #toString()} methods are insecure and
 * could lead to sensitive data written into the log files.</strong>
 */
@Log
public class JPAActivityListener {

    @PrePersist
    public void prePersist(final Object object) {
        log.info("PrePersist: " + object);
    }
    @PostPersist
    public void postPersist(final Object object) {
        log.info("PostPersist: " + object);
    }
    @PreUpdate
    public void preUpdate(final Object object) {
        log.info("PreUpdate: " + object);
    }
    @PostUpdate
    public void postUpdate(final Object object) {
        log.info("PostUpdate: " + object);
    }
    @PreRemove
    public void preRemove(final Object object) {
        log.info("PreRemove: " + object);
    }
    @PostRemove
    public void postRemove(final Object object) {
        log.info("PostRemvoe: " + object);
    }
    @PostLoad
    public void postLoad(final Object object) {
        log.info("PostLoad: " + object);
    }

}
