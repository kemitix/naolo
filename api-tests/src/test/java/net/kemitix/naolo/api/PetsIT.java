package net.kemitix.naolo.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.vertx.core.json.JsonObject;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class PetsIT {

    @Test
    @Disabled
    @DisplayName("Pets lifecycle - add - list - update - remove")
    public void pets() {
        // start with no pets
        listAllPets()
                .statusCode(SC_OK)
                .body(isEmpty());
//
//        // add a pet
//        final JsonObject newPet =
//                new JsonObject()
//                        .put("id", 0)
//                        .put("name", "Pets Name")
//                        .put("dateOfBirth", "2020-03-24")
//                        .put("type", "DOG")
//                        .put("owner_id", 42);
//        final var addResponse =
//                addPet(newPet)
//                        .statusCode(SC_CREATED);
//        final int id = Integer.parseInt(
//                URI.create(addResponse
//                        .extract()
//                        .header(HttpHeaders.LOCATION))
//                        .getPath()
//                        .split("/")[2]);
//        final JsonObject addedPet=
//                newPet.copy()
//                        .put("id", id);
//
//        // list all pets - we have one
//        final JsonArray petList=
//                new JsonArray()
//                        .add(addedPet);
//        listAllPets()
//                .statusCode(SC_OK)
//                .body(jsonArray(petList));
//
//        // get the pet
//        getPet(id)
//                .statusCode(SC_OK)
//                .body(jsonObject(addedPet));
//
//        // update the pet
//        final JsonObject updatedPet =
//                addedPet.copy()
//                        .put("name", "Pets new name");
//        updatePet(updatedPet, id)
//                .statusCode(SC_OK)
//                .body(jsonObject(updatedPet));
//
//        // get the updated pet
//        getPet(id)
//                .statusCode(SC_OK)
//                .body(jsonObject(updatedPet));
//
//        // list all pets - has the updated one
//        final JsonArray updatedPetList =
//                new JsonArray()
//                        .add(updatedPet);
//        listAllPets()
//                .statusCode(SC_OK)
//                .body(jsonArray(updatedPetList));
//
//        // remove pet
//        deletePet(id)
//                .statusCode(SC_OK);
//
//        // get pet - not found
//        getPet(id)
//                .statusCode(SC_NOT_FOUND);
//
//        // list all pets - none found
//        listAllPets()
//                .statusCode(SC_OK)
//                .body(isEmpty());
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
