package net.kemitix.naolo;

import net.kemitix.naolo.storage.*;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractEntityResource<T extends HasId>
        implements EntityResource<T> {

    private final ListEntityUseCase<T> listAll;
    private final AddEntityUseCase<T> addEntity;
    private final GetEntityUseCase<T> getEntity;
    private final UpdateEntityUseCase<T> updateEntity;
    private final RemoveEntityUseCase<T> removeEntity;

    protected AbstractEntityResource(
            final ListEntityUseCase<T> listAll,
            final AddEntityUseCase<T> addEntity,
            final GetEntityUseCase<T> getEntity,
            final UpdateEntityUseCase<T> updateEntity,
            final RemoveEntityUseCase<T> removeEntity
    ) {
        this.listAll = listAll;
        this.addEntity = addEntity;
        this.getEntity = getEntity;
        this.updateEntity = updateEntity;
        this.removeEntity = removeEntity;
    }

    protected List<T> doAll() {
        final ListEntityRequest<T> request = listAll.request();
        final ListEntityResponse<T> response = listAll.invoke(request);
        return response.getEntities();
    }

    protected T doGet(final long id) {
        final GetEntityRequest<T> request = getEntity.request(id);
        final GetEntityResponse<T> response = getEntity.invoke(request);
        return response.getEntity()
                .orElseThrow(ownerNotFound(id));
    }

    protected Response doAdd(final T entity) {
        final AddEntityRequest<T> request = addEntity.request(entity);
        final AddEntityResponse<T> response = addEntity.invoke(request);
        final URI location = location("/" + getPath(), response.getEntity());
        return Response.created(location).build();
    }

    abstract String getPath();

    protected T doUpdate(final T entity) {
        final UpdateEntityRequest<T> request = updateEntity.request(entity);
        final UpdateEntityResponse<T> response = updateEntity.invoke(request);
        return response.getEntity()
                .orElseThrow(ownerNotFound(entity.getId()));
    }

    protected T doRemove(final long id) {
        final RemoveEntityRequest<T> request = removeEntity.request(id);
        final RemoveEntityResponse<T> response = removeEntity.invoke(request);
        return response.getEntity()
                .orElseThrow(ownerNotFound(id));
    }

    private Supplier<NotFoundException> ownerNotFound(final long id) {
        return () ->
                new NotFoundException(String.format(
                        "Owner with id %d not found",
                        id
                ));
    }

    protected URI location(
            final String path,
            final T entity
    ) {
        final Long id = entity.getId();
        return URI.create(String.format(
                path + "/%d", id));
    }

}
