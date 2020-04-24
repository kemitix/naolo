package net.kemitix.naolo.core;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.*;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EntityResourceTest
        implements WithAssertions {

    private final ListEntityUseCase<Example> listAll;
    private final AddEntityUseCase<Example> addEntity;
    private final GetEntityUseCase<Example> getEntity;
    private final UpdateEntityUseCase<Example> updateEntity;
    private final RemoveEntityUseCase<Example> removeEntity;
    private final ExampleEntityResource resource;

    public EntityResourceTest(
            @Mock final ListEntityUseCase<Example> listAll,
            @Mock final AddEntityUseCase<Example> addEntity,
            @Mock final GetEntityUseCase<Example> getEntity,
            @Mock final UpdateEntityUseCase<Example> updateEntity,
            @Mock final RemoveEntityUseCase<Example> removeEntity
    ) {
        this.listAll = listAll;
        this.addEntity = addEntity;
        this.getEntity = getEntity;
        this.updateEntity = updateEntity;
        this.removeEntity = removeEntity;
        resource = new ExampleEntityResource(
                this.listAll, this.addEntity, this.getEntity, this.updateEntity, this.removeEntity);
    }

    @Test
    @DisplayName("doAll invokes the use case")
    public void doAllInvokesUseCase() {
        //given
        final List<Example> examples = Collections.singletonList(new Example());
        given(listAll.invoke(any())).willReturn(() -> examples);
        //when
        final List<Example> all = resource.all();
        //then
        assertThat(all).isEqualTo(examples);
    }

    @Test
    @DisplayName("doGet invokes the use case")
    public void doGetInvokesUseCase() {
        //given
        final Example example = new Example();
        given(getEntity.invoke(any())).willReturn(() -> Optional.of(example));
        //when
        final Example result = resource.get(example.id);
        //then
        assertThat(result).isEqualTo(example);
    }

    @Test
    @DisplayName("doGet invokes the use case where not found")
    public void doGetWhereNotFoundInvokesUseCase() {
        //given
        final Example example = new Example();
        given(getEntity.invoke(any())).willReturn(Optional::empty);
        //then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> resource.get(example.id));
    }

    @Test
    @DisplayName("doAdd invokes the use case")
    public void doAddInvokesUseCase() {
        //given
        final Example example = new Example();
        given(addEntity.invoke(any())).willReturn(() -> example);
        //when
        final Response response = resource.add(example);
        //then
        assertThat(response.getLocation()).isEqualTo(
                URI.create(String.format("/%s/%d", resource.path, example.id)));
    }

    @Test
    @DisplayName("doUpdate invokes the use case")
    public void doUpdateInvokesUseCase() {
        //given
        final Example example = new Example();
        given(updateEntity.invoke(any())).willReturn(() -> Optional.of(example));
        //when
        final Example update = resource.update(example.id, example);
        //then
        assertThat(update).isEqualTo(example);
    }

    @Test
    @DisplayName("doRemove invokes the use case")
    public void doRemoveInvokesUseCase() {
        //given
        final Example example = new Example();
        given(removeEntity.invoke(any())).willReturn(() -> Optional.of(example));
        //when
        final Example result = resource.remove(example.id);
        //then
        assertThat(result).isEqualTo(example);
    }

    private static class Example implements HasId {
        @Getter
        private final long id = new Random().nextLong();
    }

    private class ExampleEntityResource
            extends AbstractEntityResource<Example> {

        private final String path = UUID.randomUUID().toString();

        protected ExampleEntityResource(
                final ListEntityUseCase<Example> listAll,
                final AddEntityUseCase<Example> addEntity,
                final GetEntityUseCase<Example> getEntity,
                final UpdateEntityUseCase<Example> updateEntity,
                final RemoveEntityUseCase<Example> removeEntity
        ) {
            super(listAll, addEntity, getEntity, updateEntity, removeEntity);
        }

        @Override
        protected String getPath() {
            return path;
        }

        @Override
        public List<Example> all() {
            return doAll();
        }

        @Override
        public Example get(final long id) {
            return doGet(id);
        }

        @Override
        public Response add(final Example entity) {
            return doAdd(entity);
        }

        @Override
        public Example update(final long id, final Example entity) {
            return doUpdate(entity);
        }

        @Override
        public Example remove(final long id) {
            return doRemove(id);
        }
    }
}
