package net.kemitix.naolo;

import lombok.extern.java.Log;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Log
@Path(OwnerResource.PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OwnerResource
        extends EntityResource<Owner> {

    public static final String PATH = "owners";

    public OwnerResource() {
        super(null,
                null,
                null,
                null,
                null);
    }

    @Inject
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
    public List<Owner> all() {
        return doAll();
    }

    @GET
    @Path("{id}")
    @Override
    public Owner get(@PathParam("id") final long id) {
        return doGet(id);
    }

    @Transactional
    @POST
    @Override
    public Response add(final Owner entity) {
        return doAdd(entity);
    }

    @Transactional
    @PUT
    @Path("{id}")
    @Override
    public Owner update(
            @PathParam("id") final long id,
            final Owner entity
    ) {
        return doUpdate(entity);
    }

    @Transactional
    @DELETE
    @Path("{id}")
    @Override
    public Owner remove(@PathParam("id") final long id) {
        return doRemove(id);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

}
