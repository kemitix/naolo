package net.kemitix.naolo.api;

import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.JsonArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.kemitix.naolo.api.JsonMatcher.jsonArray;
import static net.kemitix.naolo.api.JsonMatcher.jsonObject;
import static org.hamcrest.CoreMatchers.is;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_CREATED;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_NOT_FOUND;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_OK;

@QuarkusTest
public class VetsIT
        extends AbstractIT {

    @Test
    @DisplayName("Vets lifecycle - add - list - update - remove")
    public void vets() {
        // start with no vets
        listAllVets()
                .statusCode(SC_OK)
                .body(is("[]"));

        // add one
        final var newVet = createVet();
        final long vetId = getIdFromLocationHeader(
                addVet(newVet)
                        .statusCode(SC_CREATED));
        final var addedVet = newVet.copy().put("id", vetId);

        // now we have a vet
        final var vetList = new JsonArray().add(addedVet);
        listAllVets()
                .statusCode(SC_OK)
                .body(jsonArray(vetList));

        // fetch our singular vet
        getVet(vetId)
                .statusCode(SC_OK)
                .body(jsonObject(addedVet));

        // update the vet
        final var updatedVet =
                addedVet.copy()
                        .put("name", "new name")
                        .put("specialisations", new JsonArray()
                                .add("SURGERY"));
        updateVet(updatedVet, vetId)
                .statusCode(SC_OK)
                .body(jsonObject(updatedVet));

        // fetch our updated vet
        getVet(vetId)
                .statusCode(SC_OK)
                .body(jsonObject(updatedVet));

        // list all vets - get updated list
        final var updatedVetList=
                new JsonArray()
                        .add(updatedVet);
        listAllVets()
                .statusCode(SC_OK)
                .body(jsonArray(updatedVetList));

        // delete the vet
        deleteVet(vetId)
                .statusCode(SC_OK);

        // get vet - not found
        getVet(vetId)
                .statusCode(SC_NOT_FOUND);

        // finish with no vets
        listAllVets()
                .statusCode(SC_OK)
                .body(is("[]"));
    }

}
