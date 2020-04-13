package net.kemitix.naolo.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.kemitix.naolo.api.JsonMatcher.jsonArray;
import static net.kemitix.naolo.api.JsonMatcher.jsonObject;
import static org.apache.http.HttpStatus.SC_OK;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_NOT_FOUND;

@QuarkusTest
public class PetsIT
        extends AbstractIT {

    @Test
    @DisplayName("Pets lifecycle - add - list - update - remove")
    public void pets() {
        // start with no pets
        listAllPets()
                .statusCode(SC_OK)
                .body(isEmpty());

        // add a pet
        // first - add the owner
        final var newOwner = createOwner();
        final long ownerId = getIdFromLocationHeader(addOwner(newOwner));
        final var addedOwner = newOwner.copy().put("id", ownerId);
        // now add the pet
        final var newPet = createPet(addedOwner);
        final long petId = getIdFromLocationHeader(addPet(newPet));
        final var addedPet= newPet.copy().put("id", petId);

        // get the pet
        getPet(petId)
                .statusCode(SC_OK)
                .contentType(ContentType.JSON)
                .body(jsonObject(addedPet));

        // list all pets - we have one
        final JsonArray petList=
                new JsonArray()
                        .add(addedPet);
        listAllPets()
                .statusCode(SC_OK)
                .body(jsonArray(petList));

        // update the pet
        final JsonObject updatedPet =
                addedPet.copy()
                        .put("name", "Pets new name");
        updatePet(updatedPet, petId)
                .statusCode(SC_OK)
                .body(jsonObject(updatedPet));

        // get the updated pet
        getPet(petId)
                .statusCode(SC_OK)
                .body(jsonObject(updatedPet));

        // list all pets - has the updated one
        final JsonArray updatedPetList =
                new JsonArray()
                        .add(updatedPet);
        listAllPets()
                .statusCode(SC_OK)
                .body(jsonArray(updatedPetList));

        // remove pet
        deletePet(petId)
                .statusCode(SC_OK);

        // remove owner
        deleteOwner(ownerId)
                .statusCode(SC_OK);

        // get pet - not found
        getPet(petId)
                .statusCode(SC_NOT_FOUND);

        // list all pets - none found
        listAllPets()
                .statusCode(SC_OK)
                .body(isEmpty());
    }

}
