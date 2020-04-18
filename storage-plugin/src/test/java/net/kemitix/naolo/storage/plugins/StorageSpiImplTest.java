package net.kemitix.naolo.storage.plugins;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
public class StorageSpiImplTest
        implements WithAssertions {

    private final EntityManager entityManager;
    private final StorageSpiImpl storageSpi;

    public StorageSpiImplTest(@Mock final EntityManager entityManager) {
        this.entityManager = entityManager;
        storageSpi = new StorageSpiImpl(entityManager);
    }

    @Test
    @DisplayName("Provides an implementation of EntityRepository<Veterinarion>")
    public void vetRepository() {
        assertThat(storageSpi.vetRepository()).isNotNull();
    }

    @Test
    @DisplayName("Provides an implementation of EntityRepository<Owner>")
    public void ownerRepository() {
        assertThat(storageSpi.ownerRepository()).isNotNull();
    }

    @Test
    @DisplayName("Provides an implementation of EntityRepository<Pet>")
    public void petRepository() {
        assertThat(storageSpi.petRepository()).isNotNull();
    }

    @Test
    @DisplayName("Provides an implementation of EntityRepository<Visit>")
    public void visitRepository() {
        assertThat(storageSpi.visitRepository()).isNotNull();
    }
}