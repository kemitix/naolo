package net.kemitix.naolo.api;

import io.restassured.http.ContentType;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static net.kemitix.naolo.api.JsonMatcher.jsonObject;


public class VetIT {

    @Test
    @DisplayName("Add then get a single vet")
    public void addAnGetAVet() {
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
        // add one
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(newVet.encode())
                .post("/vets")
                .then()
                .statusCode(200)
                .body(jsonObject(addedVet));
        // fetch our singular vet
        given()
                .when().get("/vets/" + id)
                .then()
                .statusCode(200)
                .body(jsonObject(addedVet));

    }
}
