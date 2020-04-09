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

import net.kemitix.naolo.core.vets.AddVet;
import net.kemitix.naolo.core.vets.ListAllVets;
import net.kemitix.naolo.entities.Veterinarian;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;

import static net.kemitix.naolo.core.vets.ListAllVets.request;

/**
 * REST Controller for Veterinarians.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
@Path("/vets")
@ApplicationScoped
public class VetResource {

    private final ListAllVets listAll;
    private final AddVet addVet;

    /**
     * CDI Constructor.
     *
     * @param listAll the UseCase for List All Veterinarians
     * @param addVet
     */
    @Inject
    VetResource(
            final ListAllVets listAll,
            final AddVet addVet
    ) {
        this.listAll = listAll;
        this.addVet = addVet;
    }

    /**
     * List all Veterinarians endpoint.
     *
     * @return the respone
     * @throws ExecutionException   if there is an error completing the request
     * @throws InterruptedException if there is an error completing the request
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allVets() throws ExecutionException, InterruptedException {
        return Response.ok(
                listAll.invoke(request())
                        .getAllVeterinarians())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(final Veterinarian veterinarian) {
        final AddVet.Response response = addVet.invoke(AddVet.Request.builder()
                .veterinarian(veterinarian)
                .build());
        return Response.ok()
                .entity(response.getVeterinarian())
                .build();
    }

}
