/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Paul Campbell
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.kemitix.naolo.vets;

import lombok.extern.java.Log;
import net.kemitix.naolo.core.AbstractEntityResource;
import net.kemitix.naolo.core.jpa.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * REST Controller for Veterinarians.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
@Log
@Path(VetResource.PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class VetResource
        extends AbstractEntityResource<Veterinarian> {

    public static final String PATH = "vets";

    public VetResource() {
        super(null,
                null,
                null,
                null,
                null);
    }

    @Inject
    public VetResource(
            final ListEntityUseCase<Veterinarian> listAll,
            final AddEntityUseCase<Veterinarian> addEntity,
            final GetEntityUseCase<Veterinarian> getEntity,
            final UpdateEntityUseCase<Veterinarian> updateEntity,
            final RemoveEntityUseCase<Veterinarian> removeEntity
    ) {
        super(listAll, addEntity, getEntity, updateEntity, removeEntity);
    }

    @GET
    @Override
    public List<Veterinarian> all() {
        return doAll();
    }

    @GET
    @Path("{id}")
    @Override
    public Veterinarian get(@PathParam("id") final long id) {
        return doGet(id);
    }

    @Transactional
    @POST
    @Override
    public Response add(final Veterinarian entity) {
        return doAdd(entity);
    }

    @Transactional
    @PUT
    @Path("{id}")
    @Override
    public Veterinarian update(
            @PathParam("id") final long id,
            final Veterinarian entity
    ) {
        return doUpdate(entity);
    }

    @Transactional
    @DELETE
    @Path("{id}")
    @Override
    public Veterinarian remove(@PathParam("id") final long id) {
        return doRemove(id);
    }

    @Override
    protected String getPath() {
        return PATH;
    }
}
