package net.kemitix.naolo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.kemitix.naolo.core.owners.*;
import net.kemitix.naolo.entities.Owner;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Log
@Path("owners")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@RequiredArgsConstructor
public class OwnerResource {

    private static final Response NOT_FOUND =
            Response.status(Response.Status.NOT_FOUND).build();

    private final ListAllOwners listAllOwners;
    private final AddOwner addOwner;
    private final GetOwner getOwner;
    private final UpdateOwner updateOwner;
    private final RemoveOwner removeOwner;

    @GET
    public Response allOwners() {
        log.info("GET /owners");
        final var request = ListAllOwners.request();
        final var response = listAllOwners.invoke(request);
        return entityOk(response.getOwners());
    }

    @GET
    @Path("{id}")
    public Response getOwner(@PathParam("id") final long id) {
        log.info("GET /owners/" + id);
        final var request = GetOwner.Request.builder()
                .id(id).build();
        final var response = getOwner.invoke(request);
        return response.getOwner()
                .map(this::entityOk)
                .orElse(NOT_FOUND);
    }

    @POST
    public Response add(final Owner owner) {
        log.info(String.format("POST /owners (%s - %s, %s)",
                owner.getId(), owner.getLastName(), owner.getFirstName()));
        final var request = AddOwner.Request.builder()
                .owner(owner).build();
        final var response = addOwner.invoke(request);
        final var id = response.getOwner().getId();
        final var location = URI.create(String.format(
                "/owners/%d", id));
        return Response.created(location).build();
    }

    @PUT
    @Path("{id}")
    public Response update(
            @PathParam("id") final long id,
            final Owner owner
    ) {
        log.info(String.format("PUT /owners/%d", id));
        final var request = UpdateOwner.Request.builder()
                .owner(owner).build();
        final var response = updateOwner.invoke(request);
        return response.getOwner()
                .map(this::entityOk)
                .orElse(NOT_FOUND);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") final long id) {
        log.info(String.format("DELETE /owners/%d", id));
        final var request = RemoveOwner.Request.builder()
                .id(id).build();
        final var response = removeOwner.invoke(request);
        return response.getOwner()
                .map(e -> Response.ok().build())
                .orElse(NOT_FOUND);
    }

    private Response entityOk(final Object entity) {
        return Response.ok().entity(entity).build();
    }
}
