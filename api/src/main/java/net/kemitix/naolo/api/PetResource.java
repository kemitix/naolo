package net.kemitix.naolo.api;

import lombok.extern.java.Log;
import net.kemitix.naolo.core.*;
import net.kemitix.naolo.entities.Pet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Log
@Path(PetResource.PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PetResource
        extends EntityResource<Pet> {

    public static final String PATH = "pets";

    @Inject
    protected PetResource(
            final ListEntityUseCase<Pet> listAll,
            final AddEntityUseCase<Pet> addEntity,
            final GetEntityUseCase<Pet> getEntity,
            final UpdateEntityUseCase<Pet> updateEntity,
            final RemoveEntityUseCase<Pet> removeEntity
    ) {
        super(listAll, addEntity, getEntity, updateEntity, removeEntity);
    }

    public PetResource() {
        super(null,
                null,
                null,
                null,
                null);
    }

    @GET
    @Override
    public List<Pet> all() {
        return doAll();
    }

    @GET
    @Path("{id}")
    @Override
    public Pet get(@PathParam("id") final long id) {
        return doGet(id);
    }

    @Transactional
    @POST
    @Override
    public Response add(final Pet entity) {
        return doAdd(entity);
    }

    @Transactional
    @PUT
    @Path("{id}")
    @Override
    public Pet update(
            @PathParam("id") final long id,
            final Pet entity
    ) {
        return doUpdate(entity);
    }

    @Transactional
    @DELETE
    @Path("{id}")
    @Override
    public Pet remove(@PathParam("id") final long id) {
        return doRemove(id);
    }

    @Override
    protected String getPath() {
        return PATH;
    }
}
