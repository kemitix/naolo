package net.kemitix.naolo.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.LOCATION;
import static net.kemitix.naolo.api.JsonMatcher.jsonArray;
import static net.kemitix.naolo.api.JsonMatcher.jsonObject;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_CREATED;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_NOT_FOUND;

@QuarkusTest
public class PetsIT {

    @Test
    @DisplayName("Pets lifecycle - add - list - update - remove")
    public void pets() {
        // start with no pets
        listAllPets()
                .statusCode(SC_OK)
                .body(isEmpty());

        // add a pet
        // first - add the owner
        final var newOwner = new JsonObject()
                .put("id", 0)
                .put("firstName", "Bob")
                .put("lastName", "Smith")
                .put("street", "the road")
                .put("city", "capital");
        final URI ownerUri =
                URI.create(given()
                        .contentType(ContentType.JSON)
                        .body(newOwner.encode())
                        .post("/owners")
                        .then()
                        .statusCode(SC_CREATED)
                        .extract()
                        .header(LOCATION));
        final String ownerId =
                ownerUri.getPath()
                        .split("/")[2];
        final var addedOwner = newOwner.copy()
                .put("id", Long.valueOf(ownerId));

        // now add the pet
        final JsonObject newPet =
                new JsonObject()
                        .put("id", 0)
                        .put("name", "Pets Name")
                        .put("dateOfBirth", "2020-03-24")
                        .put("type", "DOG")
                        .put("owner", addedOwner);
        final var addResponse =
                addPet(newPet)
                        .statusCode(SC_CREATED);
        final int id = Integer.parseInt(
                URI.create(addResponse
                        .extract()
                        .header(LOCATION))
                        .getPath()
                        .split("/")[2]);
        final JsonObject addedPet=
                newPet.copy()
                        .put("id", id);
        System.out.println("Added Pet - okay - " + id);
        // get the pet
        getPet(id)
                .statusCode(SC_OK)
                .contentType(ContentType.JSON)
                .body(jsonObject(addedPet));
        System.out.println("Get Pet - okay");
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
        updatePet(updatedPet, id)
                .statusCode(SC_OK)
                .body(jsonObject(updatedPet));

        // get the updated pet
        getPet(id)
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
        deletePet(id)
                .statusCode(SC_OK);

        // remove owner
        given()
                .delete("/owners/" + ownerId)
                .then()
                .statusCode(SC_OK);

        // get pet - not found
        getPet(id)
                .statusCode(SC_NOT_FOUND);

        // list all pets - none found
        listAllPets()
                .statusCode(SC_OK)
                .body(isEmpty());
    }

    private ValidatableResponse deletePet(final int id) {
        return given()
                .delete("/pets/" + id)
                .then();
    }

    private ValidatableResponse updatePet(
            final JsonObject pet,
            final int id
    ) {
        return given()
                .contentType(ContentType.JSON)
                .body(pet.encode())
                .put("/pets/" + id)
                .then();
    }

    private ValidatableResponse addPet(final JsonObject pet) {
        return given()
                .contentType(ContentType.JSON)
                .body(pet.encode())
                .post("/pets")
                .then();
    }

    private ValidatableResponse getPet(final int id) {
        return given()
                .get("/pets/" + id)
                .then();
    }

    private Matcher<String> isEmpty() {
        return is("[]");
    }

    private ValidatableResponse listAllPets() {
        return given()
                .get("/pets").then();
    }
}
