package net.kemitix.naolo.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.http.HttpHeaders;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static net.kemitix.naolo.api.JsonMatcher.jsonArray;
import static net.kemitix.naolo.api.JsonMatcher.jsonObject;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class OwnersIT {

    @Test
    @DisplayName("Owners lifecycle - add - list - update - remove")
    public void owners() {
        // start with no Owners
        listAllOwners()
                .statusCode(SC_OK)
                .body(isEmpty());

        // add an Owner
        final JsonObject newOwner =
                new JsonObject()
                        .put("id", 0)
                        .put("firstName", "Owners First Name")
                        .put("lastName", "Owners Last Name")
                        .put("street", "Owners Street Address")
                        .put("city", "Owners City");
        final var addResponse =
                addOwner(newOwner)
                        .statusCode(SC_CREATED);
        final int id = Integer.parseInt(
                URI.create(addResponse
                        .extract()
                        .header(HttpHeaders.LOCATION))
                        .getPath()
                        .split("/")[2]);
        final JsonObject addedOwner=
                newOwner.copy()
                        .put("id", id);

        // list all owners - we have one
        final JsonArray ownerList=
                new JsonArray()
                        .add(addedOwner);
        listAllOwners()
                .statusCode(SC_OK)
                .body(jsonArray(ownerList));

        // get the owner
        getOwner(id)
                .statusCode(SC_OK)
                .body(jsonObject(addedOwner));

        // update the owner
        final JsonObject updatedOwner =
                addedOwner.copy()
                        .put("street", "New Street");
        updateOwner(updatedOwner, id)
                .statusCode(SC_OK)
                .body(jsonObject(updatedOwner));

        // get the updated owner
        getOwner(id)
                .statusCode(SC_OK)
                .body(jsonObject(updatedOwner));

        // list all owners - has the updated one
        final JsonArray updatedOwnerList =
                new JsonArray()
                        .add(updatedOwner);
        listAllOwners()
                .statusCode(SC_OK)
                .body(jsonArray(updatedOwnerList));

        // remove owner
        deleteOwner(id)
                .statusCode(SC_OK);

        // get owner - not found
        getOwner(id)
                .statusCode(SC_NOT_FOUND);

        // list all owners - none found
        listAllOwners()
                .statusCode(SC_OK)
                .body(isEmpty());
    }

    private ValidatableResponse deleteOwner(final int id) {
        return given()
                .delete("/owners/" + id)
                .then();
    }

    private ValidatableResponse updateOwner(
            final JsonObject updatedOwner,
            final int id
    ) {
        return given()
                .contentType(ContentType.JSON)
                .body(updatedOwner.encode())
                .put("/owners/" + id)
                .then();
    }

    private ValidatableResponse addOwner(final JsonObject newOwner) {
        return given()
                .contentType(ContentType.JSON)
                .body(newOwner.encode())
                .post("/owners")
                .then();
    }

    private ValidatableResponse getOwner(final int id) {
        return given()
                .get("/owners/" + id)
                .then();
    }

    private Matcher<String> isEmpty() {
        return is("[]");
    }

    private ValidatableResponse listAllOwners() {
        return given()
                .get("/owners").then();
    }
}
