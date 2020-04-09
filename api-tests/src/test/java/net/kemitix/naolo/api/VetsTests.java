package net.kemitix.naolo.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class VetsTests {

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
                                .add("RADIOLOGY")
                                .add("DENTISTRY"));
        final JsonObject addedVet =
                newVet.copy()
                        .put("id", 1);
        final JsonArray vetList =
                new JsonArray()
                        .add(addedVet);
        // start with no vets
        given()
                .when().get(PATH)
                .then()
                .statusCode(200)
                .body(is(empty));
        // add one
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(newVet.encode())
                .post(PATH)
                .then()
                .statusCode(200)
                .body(is(addedVet.encode()));
        // now we have a vet
        given()
                .when().get(PATH)
                .then()
                .statusCode(200)
                .body(is(vetList.encode()));
    }
}
