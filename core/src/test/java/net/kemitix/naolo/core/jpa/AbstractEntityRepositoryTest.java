package net.kemitix.naolo.core.jpa;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class AbstractEntityRepositoryTest
        implements WithAssertions {

    private final EntityManager entityManager;

    public AbstractEntityRepositoryTest(
            @Mock final EntityManager entityManager
    ) {
        this.entityManager = entityManager;
    }

    @Test
    @DisplayName("Has Required No-Args Constructor")
    public void hasRequiredNoArgsConstructor() {
        assertThatCode(FakeRepository::new)
                .doesNotThrowAnyException();
    }

    private class FakeRepository
            extends AbstractEntityRepository<Entity> {
        private FakeRepository() {
            super(entityManager);
        }

        @Override
        public Stream<Entity> findAll() {
            return null;
        }

        @Override
        public Optional<Entity> find(final long id) {
            return Optional.empty();
        }

        @Override
        public Optional<Entity> update(final Entity entity) {
            return Optional.empty();
        }
    }

    private class Entity {
    }
}
