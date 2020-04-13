package net.kemitix.naolo.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.kemitix.naolo.api.JsonMatcher.jsonArray;
import static net.kemitix.naolo.api.JsonMatcher.jsonObject;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_NOT_FOUND;

@QuarkusTest
public class VisitsIT
        extends AbstractIT {

    @Test
    @DisplayName("Visit lifecycle - add - list - update - remove")
    public void visit() {
        // start with no visits
        listAllVisits()
                .statusCode(SC_OK)
                .body(isEmpty());

        // add a visit
        // first - add the owner
        final var newOwner = createOwner();
        final long ownerId = getIdFromLocationHeader(
                addOwner(newOwner).statusCode(SC_CREATED));
        final var addedOwner = newOwner.copy().put("id", ownerId);
        // then add the pet
        final var newPet = createPet(addedOwner);
        final long petId = getIdFromLocationHeader(
                addPet(newPet).statusCode(SC_CREATED));
        final var addedPet= newPet.copy().put("id", petId);
        // then add the vet
        final var newVet = createVet();
        final long vetId = getIdFromLocationHeader(
                addVet(newVet).statusCode(SC_CREATED));
        final var addedVet = newVet.copy().put("id", vetId);
        // now add the visit
        final var newVisit = createVisit(addedPet, addedVet);
        final long visitId = getIdFromLocationHeader(
                addVisit(newVisit).statusCode(SC_CREATED));
        final var addedVisit = newVisit.copy().put("id", visitId);

        // get the visit
        getVisit(visitId)
                .statusCode(SC_OK)
                .contentType(ContentType.JSON)
                .body(jsonObject(addedVisit));

        // list all visits - we have one
        final var visitList = new JsonArray().add(addedPet);
        listAllPets()
                .statusCode(SC_OK)
                .body(jsonArray(visitList));

        // update the pet
        final var updatedVisit = addedVisit.copy()
                .put("description", "Visit to the vet");
        updateVisit(updatedVisit, visitId)
                .statusCode(SC_OK)
                .body(jsonObject(updatedVisit));

        // get the updated visit
        getVisit(visitId)
                .statusCode(SC_OK)
                .body(jsonObject(updatedVisit));

        // list all visits - has the updated one
        final var updatedVisitList = new JsonArray().add(updatedVisit);
        listAllVisits()
                .statusCode(SC_OK)
                .body(jsonArray(updatedVisitList));

        // remove visit
        deleteVisit(visitId).statusCode(SC_OK);

        // clean up - remove pet
        deletePet(petId).statusCode(SC_OK);

        // clean up - remove owner
        deleteOwner(ownerId).statusCode(SC_OK);

        // clean up - remove vet
        deleteVet(vetId).statusCode(SC_OK);

        // get pet - not found
        getPet(petId).statusCode(SC_NOT_FOUND);

        // list all pets - none found
        listAllPets()
                .statusCode(SC_OK)
                .body(isEmpty());
    }

}
