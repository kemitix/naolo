package net.kemitix.naolo.api;

import net.kemitix.naolo.entities.HasId;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

public class EntityResourceTest
        implements WithAssertions {
    @Test
    @DisplayName("Has Required No-Args Constructor")
    public void hasRequiredNoArgsConstructor() {
        assertThatCode(() ->
                        new FakeResource()
        ).doesNotThrowAnyException();
    }

    private class FakeResource
            extends EntityResource<Entity> {
        @Override
        Response all() {
            return null;
        }

        @Override
        Response get(final long id) {
            return null;
        }

        @Override
        Response add(final Entity entity) {
            return null;
        }

        @Override
        String getPath() {
            return null;
        }

        @Override
        Response update(final long id, final Entity entity) {
            return null;
        }

        @Override
        Response remove(final long id) {
            return null;
        }
    }

    private class Entity implements HasId {
        @Override
        public long getId() {
            return 0;
        }
    }
}