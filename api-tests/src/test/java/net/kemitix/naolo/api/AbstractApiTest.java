package net.kemitix.naolo.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.http.HttpHeaders;
import org.hamcrest.Matcher;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static net.kemitix.naolo.api.AbstractApiTest.Fragment.OWNERS;
import static net.kemitix.naolo.api.AbstractApiTest.Fragment.PETS;
import static net.kemitix.naolo.api.AbstractApiTest.Fragment.VETS;
import static net.kemitix.naolo.api.AbstractApiTest.Fragment.VISITS;
import static org.hamcrest.Matchers.is;

public class AbstractApiTest {
    enum Fragment {
        OWNERS("owners"),
        PETS("pets"),
        VETS("vets"),
        VISITS("visits");

        private final String fragment;

        Fragment(String fragment) {
            this.fragment = fragment;
        }

        String path() {
            String path = String.format("/%s/", fragment);
            System.out.println("path = " + path);
            return path;
        }

        String path(long id) {
            String path = path() + id;
            System.out.println("path = " + path);
            return path;
        }
    }

    protected ValidatableResponse deleteOwner(final long id) {
        return given()
                .delete(OWNERS.path(id))
                .then();
    }

    protected ValidatableResponse updateOwner(
            final JsonObject updatedOwner,
            final long id
    ) {
        return given()
                .contentType(ContentType.JSON)
                .body(updatedOwner.encode())
                .put(OWNERS.path(id))
                .then();
    }

    protected ValidatableResponse addOwner(final JsonObject newOwner) {
        return given()
                .contentType(ContentType.JSON)
                .body(newOwner.encode())
                .post(OWNERS.path())
                .then();
    }

    protected ValidatableResponse getOwner(final long id) {
        return given()
                .get(OWNERS.path(id))
                .then();
    }

    protected ValidatableResponse deletePet(final long id) {
        return given()
                .delete(PETS.path(id))
                .then();
    }

    protected ValidatableResponse updatePet(
            final JsonObject pet,
            final long id
    ) {
        return given()
                .contentType(ContentType.JSON)
                .body(pet.encode())
                .put(PETS.path(id))
                .then();
    }

    protected ValidatableResponse addPet(final JsonObject pet) {
        return given()
                .contentType(ContentType.JSON)
                .body(pet.encode())
                .post(PETS.path())
                .then();
    }

    protected ValidatableResponse getPet(final long id) {
        return given()
                .get(PETS.path(id))
                .then();
    }

    protected Matcher<String> isEmpty() {
        return is("[]");
    }

    protected ValidatableResponse listAllOwners() {
        return given()
                .get(OWNERS.path())
                .then();
    }

    protected ValidatableResponse listAllVisits() {
        return given()
                .get(VISITS.path())
                .then();
    }

    protected long getIdFromLocationHeader(final ValidatableResponse addResponse) {
        return Long.parseLong(
                URI.create(addResponse
                        .extract()
                        .header(HttpHeaders.LOCATION))
                        .getPath()
                        .split("/")[2]);
    }

    protected JsonObject createOwner() {
        return new JsonObject()
                .put("id", 0)
                .put("firstName", "Owners First Name")
                .put("lastName", "Owners Last Name")
                .put("street", "Owners Street Address")
                .put("city", "Owners City");
    }

    protected JsonObject createVisit(
            final JsonObject addedPet,
            final JsonObject addedVet
    ) {
        return new JsonObject()
                .put("id", 0)
                .put("dateTime", "2020-04-13 18:00")
                .put("pet", addedPet)
                .put("veterinarian", addedVet)
                .put("description", "a visit to the vet");
    }

    protected ValidatableResponse listAllPets() {
        return given()
                .get("/pets").then();
    }

    protected JsonObject createPet(final JsonObject addedOwner) {
        return new JsonObject()
                .put("id", 0)
                .put("name", "Pets Name")
                .put("dateOfBirth", "2020-03-24")
                .put("type", "DOG")
                .put("owner", addedOwner);
    }

    protected JsonObject createVet() {
        return new JsonObject()
                .put("id", 0)
                .put("name", "Vets Name")
                .put("specialisations", new JsonArray()
                        .add("DENTISTRY")
                        .add("RADIOLOGY"));
    }

    protected ValidatableResponse deleteVet(final long id) {
        return given()
                .when().delete(VETS.path(id))
                .then();
    }

    protected ValidatableResponse deleteVisit(final long id) {
        return given()
                .when().delete(VISITS.path(id))
                .then();
    }

    protected ValidatableResponse updateVet(
            final JsonObject updatedVet,
            final long id
    ) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .body(updatedVet.encode())
                .put(VETS.path(id))
                .then();
    }

    protected ValidatableResponse updateVisit(
            final JsonObject updatedVisit,
            final long id
    ) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .body(updatedVisit.encode())
                .put(VISITS.path(id))
                .then();
    }

    protected ValidatableResponse getVet(final long id) {
        return given()
                .when().get(VETS.path(id))
                .then();
    }


    protected ValidatableResponse getVisit(final long id) {
        return given()
                .when().get(VISITS.path(id))
                .then();
    }

    protected ValidatableResponse addVet(final JsonObject newVet) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .body(newVet.encode())
                .post("/vets")
                .then();
    }

    protected ValidatableResponse addVisit(final JsonObject newVisit) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .body(newVisit.encode())
                .post("/visits")
                .then();
    }

    protected ValidatableResponse listAllVets() {
        return given()
                .when().get("/vets")
                .then();
    }
}
