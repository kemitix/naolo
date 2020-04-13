package net.kemitix.naolo.api;

import lombok.extern.java.Log;
import net.kemitix.naolo.core.*;
import net.kemitix.naolo.entities.Owner;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
@Path(OwnerResource.PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OwnerResource
        extends EntityResource<Owner> {

    public static final String PATH = "owners";

    public OwnerResource(
            final ListEntityUseCase<Owner> listAll,
            final AddEntityUseCase<Owner> addEntity,
            final GetEntityUseCase<Owner> getEntity,
            final UpdateEntityUseCase<Owner> updateEntity,
            final RemoveEntityUseCase<Owner> removeEntity
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
    @Transactional
    @Override
    public Response add(final Owner entity) {
        return doAdd(entity);
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Override
    public Response update(
            @PathParam("id") final long id,
            final Owner entity
    ) {
        return doUpdate(entity);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Override
    public Response remove(@PathParam("id") final long id) {
        return doRemove(id);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

}
