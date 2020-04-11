package net.kemitix.naolo.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static net.kemitix.naolo.api.JsonMatcher.jsonArray;
import static net.kemitix.naolo.api.JsonMatcher.jsonObject;
import static org.hamcrest.CoreMatchers.is;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_CREATED;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_NOT_FOUND;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_OK;

@QuarkusTest
public class VetsIT {

    @Test
    @DisplayName("List has no vets, then add a vet, then list has vet")
    public void listAndAddVets() {
        // start with no vets
        listAllVets()
                .statusCode(SC_OK)
                .body(is("[]"));

        // add one
        final JsonObject newVet =
                new JsonObject()
                        .put("id", 0)
                        .put("name", "Vets Name")
                        .put("specialisations", new JsonArray()
                                .add("DENTISTRY")
                                .add("RADIOLOGY"));
        final ValidatableResponse addResponse =
                addVet(newVet)
                        .statusCode(SC_CREATED);
        final int id = Integer.parseInt(
                URI.create(addResponse
                        .extract()
                        .header(HttpHeaders.LOCATION))
                        .getPath()
                        .split("/")[2]);
        final JsonObject addedVet =
                newVet.copy()
                        .put("id", id);

        // now we have a vet
        final JsonArray vetList =
                new JsonArray()
                        .add(addedVet);
        listAllVets()
                .statusCode(SC_OK)
                .body(jsonArray(vetList));

        // fetch our singular vet
        getVet(id)
                .statusCode(SC_OK)
                .body(jsonObject(addedVet));

        // update the vet
        final JsonObject updatedVet =
                addedVet.copy()
                        .put("name", "new name")
                        .put("specialisations", new JsonArray()
                                .add("SURGERY"));
        updateVet(updatedVet, id)
                .statusCode(SC_OK)
                .body(jsonObject(updatedVet));

        // fetch our updated vet
        getVet(id)
                .statusCode(SC_OK)
                .body(jsonObject(updatedVet));

        // list all vets - get updated list
        final JsonArray updatedVetList=
                new JsonArray()
                        .add(updatedVet);
        listAllVets()
                .statusCode(SC_OK)
                .body(jsonArray(updatedVetList));

        // delete the vet
        deleteVet(id)
                .statusCode(SC_OK);

        // get vet - not found
        getVet(id)
                .statusCode(SC_NOT_FOUND);

        // finish with no vets
        listAllVets()
                .statusCode(SC_OK)
                .body(is("[]"));
    }

    private ValidatableResponse deleteVet(final int id) {
        return given()
                .when().delete("/vets/" + id)
                .then();
    }

    private ValidatableResponse updateVet(
            final JsonObject updatedVet,
            final int id
    ) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .body(updatedVet.encode())
                .put("/vets/" + id)
                .then();
    }

    private ValidatableResponse getVet(final int id) {
        return given()
                .when().get("/vets/" + id)
                .then();
    }

    private ValidatableResponse addVet(final JsonObject newVet) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .body(newVet.encode())
                .post("/vets")
                .then();
    }

    private ValidatableResponse listAllVets() {
        return given()
                .when().get("/vets")
                .then();
    }

}
