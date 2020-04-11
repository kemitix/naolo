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

package net.kemitix.naolo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.kemitix.naolo.core.AddEntityRequest;
import net.kemitix.naolo.core.AddEntityResponse;
import net.kemitix.naolo.core.vets.*;
import net.kemitix.naolo.entities.Veterinarian;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * REST Controller for Veterinarians.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
@Log
@Path("vets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@RequiredArgsConstructor
public class VetResource {

    public static final Response NOT_FOUND =
            Response.status(Response.Status.NOT_FOUND).build();
    private final ListAllVets listAll;
    private final AddVet addVet;
    private final GetVet getVet;
    private final UpdateVet updateVet;
    private final RemoveVet removeVet;

    /**
     * List all Veterinarians endpoint.
     *
     * @return the respone
     */
    @GET
    public Response allVets() {
        log.info("GET /vets");
        final ListAllVets.Request request = ListAllVets.request();
        final ListAllVets.Response response = listAll.invoke(request);
        return entityOk(response.getVeterinarians());
    }

    @POST
    public Response add(final Veterinarian veterinarian) {
        log.info(String.format("POST /vets (%s - %s)",
                veterinarian.getId(), veterinarian.getName()));
        final AddEntityRequest<Veterinarian> request =
                AddEntityRequest.create(veterinarian);
        final AddEntityResponse<Veterinarian> response = addVet.invoke(request);
        final Long id = response.getEntity().getId();
        final URI location = URI.create(String.format(
                "/vets/%d", id));
        return Response.created(location).build();
    }


    @GET
    @Path("{id}")
    public Response get(@PathParam("id") final Long id) {
        log.info(String.format("GET /vets/%d", id));
        final GetVet.Request request =
                GetVet.Request.builder()
                        .id(id)
                        .build();
        final GetVet.Response response = getVet.invoke(request);
        return response
                .getVeterinarian()
                .map(this::entityOk)
                .orElse(NOT_FOUND);
    }

    private Response entityOk(final Object entity) {
        return Response.ok().entity(entity).build();
    }

    @PUT
    @Path("{id}")
    public Response update(
            @PathParam("id") final long id,
            final Veterinarian veterinarian
    ) {
        log.info(String.format("PUT /vets/%d", id));
        final UpdateVet.Request request =
                UpdateVet.Request.builder()
                        .veterinarian(veterinarian)
                        .build();
        final UpdateVet.Response response = updateVet.invoke(request);
        return response
                .getVeterinarian()
                .map(this::entityOk)
                .orElse(NOT_FOUND);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") final long id) {
        log.info(String.format("DELETE /vets/%d", id));
        final RemoveVet.Request request = RemoveVet.Request.builder()
                .id(id).build();
        final RemoveVet.Response response = removeVet.invoke(request);
        return response
                .getVeterinarian()
                .map(e -> Response.ok().build())
                .orElse(NOT_FOUND);
    }
}
