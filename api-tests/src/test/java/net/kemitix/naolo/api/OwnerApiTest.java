package net.kemitix.naolo.api;

import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.JsonArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.kemitix.naolo.api.JsonMatcher.jsonArray;
import static net.kemitix.naolo.api.JsonMatcher.jsonObject;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;

@QuarkusTest
public class OwnerApiTest
        extends AbstractApiTest {

    @Test
    @DisplayName("Owners lifecycle - add - list - update - remove")
    public void owners() {
        // start with no Owners
        listAllOwners()
                .statusCode(SC_OK)
                .body(isEmpty());

        // add an Owner
        final var newOwner = createOwner();
        final long id = getIdFromLocationHeader(
                addOwner(newOwner)
                        .statusCode(SC_CREATED));
        final var addedOwner= newOwner.copy().put("id", id);

        // list all owners - we have one
        final var ownerList= new JsonArray().add(addedOwner);
        listAllOwners()
                .statusCode(SC_OK)
                .body(jsonArray(ownerList));

        // get the owner
        getOwner(id)
                .statusCode(SC_OK)
                .body(jsonObject(addedOwner));

        // update the owner
        final var updatedOwner = addedOwner.copy()
                .put("street", "New Street");
        updateOwner(updatedOwner, id)
                .statusCode(SC_OK)
                .body(jsonObject(updatedOwner));

        // get the updated owner
        getOwner(id)
                .statusCode(SC_OK)
                .body(jsonObject(updatedOwner));

        // list all owners - has the updated one
        final var updatedOwnerList =
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

}
