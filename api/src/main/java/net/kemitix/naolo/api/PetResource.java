package net.kemitix.naolo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.kemitix.naolo.core.pets.AddPet;
import net.kemitix.naolo.entities.Pet;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
@Path("pets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@RequiredArgsConstructor
public class PetResource {

    private final AddPet addPet;

    @GET
    public Response all() {
        return null;
    }

    @Transactional
    @POST
    public Response add(final Pet pet) {
        return null;
    }

    @GET
    @Path("{id}")
    public Response get(final long id) {
        return null;
    }

    @Transactional
    @PUT
    @Path("{id}")
    public Response update(final long id, final Pet pet) {
        return null;
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public Response remove(final long id) {
        return null;
    }
}
