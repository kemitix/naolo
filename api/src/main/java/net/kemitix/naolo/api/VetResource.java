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
import net.kemitix.naolo.core.vets.AddVet;
import net.kemitix.naolo.core.vets.GetVet;
import net.kemitix.naolo.core.vets.ListAllVets;
import net.kemitix.naolo.entities.Veterinarian;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;

/**
 * REST Controller for Veterinarians.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
@Path("/vets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@RequiredArgsConstructor
public class VetResource {

    private final ListAllVets listAll;
    private final AddVet addVet;
    private final GetVet getVet;

    /**
     * List all Veterinarians endpoint.
     *
     * @return the respone
     * @throws ExecutionException   if there is an error completing the request
     * @throws InterruptedException if there is an error completing the request
     */
    @GET
    public Response allVets() throws ExecutionException, InterruptedException {
        return Response.ok(
                listAll.invoke(ListAllVets.request())
                        .getVeterinarians())
                .build();
    }

    @POST
    public Response add(final Veterinarian veterinarian) {
        final AddVet.Response response = addVet.invoke(AddVet.Request.builder()
                .veterinarian(veterinarian)
                .build());
        return Response.ok()
                .entity(response.getVeterinarian())
                .build();
    }


    @GET
    @Path("/{id}")
    public Response get(@QueryParam("id") final Long id) {
        return getVet.invoke(
                GetVet.Request.builder()
                        .id(id)
                        .build())
                .getVeterinarian().map(v ->
                        Response.ok()
                                .entity(v)
                                .build())
                .orElseGet(() ->
                        Response.status(Response.Status.NOT_FOUND)
                                .build());
    }

}
