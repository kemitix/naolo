package net.kemitix.naolo.api;

import net.kemitix.naolo.entities.HasId;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;

public interface EntityResource<T extends HasId> {
    Response NOT_FOUND =
            Response.status(Response.Status.NOT_FOUND).build();

    default Response entityOk(final Object entity) {
        return Response.ok().entity(entity).build();
    }

    @GET
    Response all();

    default URI location(
            final String path,
            final T entity
    ) {
        final Long id = entity.getId();
        return URI.create(String.format(
                path + "/%d", id));
    }

    @GET
    @Path("{id}")
    Response get(@PathParam("id") long id);

    @POST
    Response add(T entity);

    @PUT
    @Path("{id}")
    Response update(
            @PathParam("id") long id,
            T entity
    );

    @DELETE
    @Path("{id}")
    Response remove(@PathParam("id") long id);
}
