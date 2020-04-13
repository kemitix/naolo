package net.kemitix.naolo.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.*;
import net.kemitix.naolo.entities.HasId;

import javax.ws.rs.core.Response;
import java.net.URI;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(force = true)
public abstract class EntityResource<T extends HasId> {

    private final ListEntityUseCase<T> listAll;
    private final AddEntityUseCase<T> addEntity;
    private final GetEntityUseCase<T> getEntity;
    private final UpdateEntityUseCase<T> updateEntity;
    private final RemoveEntityUseCase<T> removeEntity;

    Response NOT_FOUND =
            Response.status(Response.Status.NOT_FOUND).build();

    abstract Response all();

    protected Response doAll() {
        final ListEntityRequest<T> request = listAll.request();
        final ListEntityResponse<T> response = listAll.invoke(request);
        return entityOk(response.getEntities());
    }

    abstract Response get(final long id);

    protected Response doGet(final long id) {
        final GetEntityRequest<T> request = getEntity.request(id);
        final GetEntityResponse<T> response = getEntity.invoke(request);
        return response.getEntity()
                .map(this::entityOk)
                .orElse(NOT_FOUND);
    }

    abstract Response add(T entity);

    protected Response doAdd(final T entity) {
        final AddEntityRequest<T> request = addEntity.request(entity);
        final AddEntityResponse<T> response = addEntity.invoke(request);
        final URI location = location("/" + getPath(), response.getEntity());
        return Response.created(location).build();
    }

    abstract String getPath();

    abstract Response update(final long id, final T entity);

    protected Response doUpdate(final T entity) {
        final UpdateEntityRequest<T> request = updateEntity.request(entity);
        final UpdateEntityResponse<T> response = updateEntity.invoke(request);
        return response.getEntity()
                .map(this::entityOk)
                .orElse(NOT_FOUND);
    }

    abstract Response remove(long id);

    protected Response doRemove(final long id) {
        final RemoveEntityRequest<T> request = removeEntity.request(id);
        final RemoveEntityResponse<T> response = removeEntity.invoke(request);
        return response.getEntity()
                .map(this::entityOk)
                .orElse(NOT_FOUND);
    }

    protected URI location(
            final String path,
            final T entity
    ) {
        final Long id = entity.getId();
        return URI.create(String.format(
                path + "/%d", id));
    }

    protected Response entityOk(final Object entity) {
        return Response.ok().entity(entity).build();
    }

}
