package net.kemitix.naolo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.kemitix.naolo.core.pets.*;
import net.kemitix.naolo.entities.Pet;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Log
@Path("pets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@RequiredArgsConstructor
public class PetResource
        implements EntityResource<Pet> {

    private final ListPets listPets;
    private final AddPet addPet;
    private final GetPet getPet;
    private final UpdatePet updatePet;
    private final RemovePet removePet;

    @Override
    @GET
    public Response all() {
        log.info("GET /pets");
        final var request = ListPets.request();
        final var response = listPets.invoke(request);
        return entityOk(response.getEntities());
    }

    @Override
    @Transactional
    @POST
    public Response add(final Pet pet) {
        log.info("POST /pets");
        final var request = AddPet.request(pet);
        final var response = addPet.invoke(request);
        final URI location = location("/pets", response.getEntity());
        return Response.created(location).build();    }

    @Override
    @GET
    @Path("{id}")
    public Response get(final long id) {
        log.info("GET /pets/" + id);
        final var request = GetPet.request(id);
        final var response = getPet.invoke(request);
        return response.getEntity()
                .map(this::entityOk)
                .orElse(NOT_FOUND);
    }

    @Override
    @Transactional
    @PUT
    @Path("{id}")
    public Response update(final long id, final Pet pet) {
        log.info("PUT /pets/" + id);
        final var request = UpdatePet.request(pet);
        final var response = updatePet.invoke(request);
        return response.getEntity()
                .map(this::entityOk)
                .orElse(NOT_FOUND);
    }

    @Override
    @Transactional
    @DELETE
    @Path("{id}")
    public Response remove(final long id) {
        log.info("DELETE /pets/" + id);
        final var request = RemovePet.request(id);
        final var response = removePet.invoke(request);
        return response.getEntity()
                .map(this::entityOk)
                .orElse(NOT_FOUND);
    }
}
