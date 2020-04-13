package net.kemitix.naolo.api;

import lombok.extern.java.Log;
import net.kemitix.naolo.core.*;
import net.kemitix.naolo.entities.Pet;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
@Path(PetResource.PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PetResource
        extends EntityResource<Pet> {

    public static final String PATH = "pets";

    protected PetResource(
            final ListEntityUseCase<Pet> listAll,
            final AddEntityUseCase<Pet> addEntity,
            final GetEntityUseCase<Pet> getEntity,
            final UpdateEntityUseCase<Pet> updateEntity,
            final RemoveEntityUseCase<Pet> removeEntity
    ) {
        super(listAll, addEntity, getEntity, updateEntity, removeEntity);
    }

    @GET
    @Override
    public Response all() {
        return doAll();
    }

    @GET
    @Path("{id}")
    @Override
    public Response get(@PathParam("id") final long id) {
        return doGet(id);
    }

    @POST
    @Override
    public Response add(final Pet entity) {
        return doAdd(entity);
    }

    @PUT
    @Path("{id}")
    @Override
    public Response update(
            @PathParam("id") final long id,
            final Pet entity
    ) {
        return doUpdate(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public Response remove(@PathParam("id") final long id) {
        return doRemove(id);
    }

    @Override
    protected String getPath() {
        return PATH;
    }
}
