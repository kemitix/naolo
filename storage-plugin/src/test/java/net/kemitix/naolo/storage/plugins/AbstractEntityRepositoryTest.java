package net.kemitix.naolo.storage.plugins;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Stream;

public class AbstractEntityRepositoryTest
        implements WithAssertions {

        @Test
        @DisplayName("Has Required No-Args Constructor")
        public void hasRequiredNoArgsConstructor() {
            assertThatCode(() ->
                    new FakeRepository()
            ).doesNotThrowAnyException();
        }

    private class FakeRepository
            extends AbstractEntityRepository<Entity>{
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