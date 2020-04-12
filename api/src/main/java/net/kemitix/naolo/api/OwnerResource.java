package net.kemitix.naolo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.kemitix.naolo.core.AddEntityRequest;
import net.kemitix.naolo.core.owners.*;
import net.kemitix.naolo.entities.Owner;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
@Path("owners")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@RequiredArgsConstructor
public class OwnerResource
        implements EntityResource<Owner> {

    private final ListAllOwners listAllOwners;
    private final AddOwner addOwner;
    private final GetOwner getOwner;
    private final UpdateOwner updateOwner;
    private final RemoveOwner removeOwner;

    @Override
    @GET
    public Response all() {
        log.info("GET /owners");
        final var request = ListAllOwners.request();
        final var response = listAllOwners.invoke(request);
        return entityOk(response.getOwners());
    }

    @Override
    @GET
    @Path("{id}")
    public Response get(@PathParam("id") final long id) {
        log.info("GET /owners/" + id);
        final var request = GetOwner.Request.builder()
                .id(id).build();
        final var response = getOwner.invoke(request);
        return response.getOwner()
                .map(this::entityOk)
                .orElse(EntityResource.NOT_FOUND);
    }

    @Override
    @POST
    public Response add(final Owner owner) {
        log.info(String.format("POST /owners (%s - %s, %s)",
                owner.getId(), owner.getLastName(), owner.getFirstName()));
        final var request = AddEntityRequest.create(owner);
        final var response = addOwner.invoke(request);
        final var location = location("/owners", response.getEntity());
        return Response.created(location).build();
    }

    @Override
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
                .orElse(EntityResource.NOT_FOUND);
    }

    @Override
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") final long id) {
        log.info(String.format("DELETE /owners/%d", id));
        final var request = RemoveOwner.Request.builder()
                .id(id).build();
        final var response = removeOwner.invoke(request);
        return response.getOwner()
                .map(e -> Response.ok().build())
                .orElse(EntityResource.NOT_FOUND);
    }

}
