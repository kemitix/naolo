package net.kemitix.naolo.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static net.kemitix.naolo.api.JsonMatcher.jsonArray;
import static net.kemitix.naolo.api.JsonMatcher.jsonObject;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class VetsIT {

    private static final String PATH = "/vets";

    @Test
    @DisplayName("List has no vets, then add a vet, then list has vet")
    public void listAndAddVets() {
        final String empty = "[]";
        final JsonObject newVet =
                new JsonObject()
                        .put("id", 0)
                        .put("name", "Vets Name")
                        .put("specialisations", new JsonArray()
                                .add("DENTISTRY")
                                .add("RADIOLOGY"));
        final int id = 1;
        final JsonObject addedVet =
                newVet.copy()
                        .put("id", id);
        final JsonArray vetList =
                new JsonArray()
                        .add(addedVet);
        final JsonObject updatedVet =
                addedVet.copy()
                        .put("name", "new name")
                        .put("specialisations", new JsonArray()
                                .add("SURGERY"));
        // start with no vets
        given()
                .when().get(PATH)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(is(empty));
        // add one
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(newVet.encode())
                .post(PATH)
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        // now we have a vet
        given()
                .when().get(PATH)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(jsonArray(vetList));
        // fetch our singular vet
        given()
                .when().get("/vets/" + id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(jsonObject(addedVet));
        // update the vet
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(updatedVet.encode())
                .put("/vets/" + id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(jsonObject(updatedVet));
        // fetch our updated vet
        given()
                .when().get("/vets/" + id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(jsonObject(updatedVet));
        // delete the vet
        given()
                .when().delete("/vets/" + id)
                .then()
                .statusCode(HttpStatus.SC_OK);
        // finish with no vets
        given()
                .when().get(PATH)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(is(empty));
    }

}
