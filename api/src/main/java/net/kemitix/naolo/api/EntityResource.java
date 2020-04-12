package net.kemitix.naolo.api;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public interface EntityResource<T> {
    Response NOT_FOUND =
            Response.status(Response.Status.NOT_FOUND).build();

    default Response entityOk(final Object entity) {
        return Response.ok().entity(entity).build();
    }

    @GET
    Response all();

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
