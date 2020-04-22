package net.kemitix.naolo;

import net.kemitix.naolo.storage.*;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AbstractEntityResourceTest
        implements WithAssertions {


    private final ListEntityUseCase<Entity> listAll;
    private final AddEntityUseCase<Entity> addEntity;
    private final GetEntityUseCase<Entity> getEntity;
    private final UpdateEntityUseCase<Entity> updateEntity;
    private final RemoveEntityUseCase<Entity> removeEntity;

    public AbstractEntityResourceTest(
            @Mock final ListEntityUseCase<Entity> listAll,
            @Mock final AddEntityUseCase<Entity> addEntity,
            @Mock final GetEntityUseCase<Entity> getEntity,
            @Mock final UpdateEntityUseCase<Entity> updateEntity,
            @Mock final RemoveEntityUseCase<Entity> removeEntity
    ) {
        this.listAll = listAll;
        this.addEntity = addEntity;
        this.getEntity = getEntity;
        this.updateEntity = updateEntity;
        this.removeEntity = removeEntity;
    }

    @Test
    @DisplayName("Has Required No-Args Constructor")
    public void hasRequiredNoArgsConstructor() {
        assertThatCode(FakeResource::new)
                .doesNotThrowAnyException();
    }

    private class FakeResource
            extends AbstractEntityResource<Entity> {

        protected FakeResource() {
            super(
                    listAll,
                    addEntity,
                    getEntity,
                    updateEntity,
                    removeEntity);
        }

        @Override
        List<Entity> all() {
            return null;
        }

        @Override
        Entity get(final long id) {
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
        Entity update(final long id, final Entity entity) {
            return null;
        }

        @Override
        Entity remove(final long id) {
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