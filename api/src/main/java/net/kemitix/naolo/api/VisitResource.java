package net.kemitix.naolo.api;


import lombok.extern.java.Log;
import net.kemitix.naolo.core.*;
import net.kemitix.naolo.entities.Visit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Log
@Path(VisitResource.PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class VisitResource
        extends EntityResource<Visit> {

    public static final String PATH = "visits";

    @Inject
    public VisitResource(
            final ListEntityUseCase<Visit> listAll,
            final AddEntityUseCase<Visit> addEntity,
            final GetEntityUseCase<Visit> getEntity,
            final UpdateEntityUseCase<Visit> updateEntity,
            final RemoveEntityUseCase<Visit> removeEntity
    ) {
        super(listAll, addEntity, getEntity, updateEntity, removeEntity);
    }

    public VisitResource() {
        super(null,
                null,
                null,
                null,
                null);
    }

    @GET
    @Override
    public List<Visit> all() {
        return doAll();
    }

    @GET
    @Path("{id}")
    @Override
    public Visit get(@PathParam("id") final long id) {
        return doGet(id);
    }

    @POST
    @Override
    public Response add(final Visit entity) {
        return doAdd(entity);
    }

    @PUT
    @Path("{id}")
    @Override
    public Visit update(
            @PathParam("id") final long id,
            final Visit entity
    ) {
        return doUpdate(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public Visit remove(@PathParam("id") final long id) {
        return doRemove(id);
    }

    @Override
    protected String getPath() {
        return PATH;
    }
}
