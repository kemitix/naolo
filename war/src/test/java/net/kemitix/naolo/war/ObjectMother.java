package net.kemitix.naolo.war;

import net.kemitix.naolo.entities.Owner;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.net.URI;

public class ObjectMother {

    public static Owner getNewOwner() {
        return new Owner()
                .withId(0)
                .withFirstName("Owners First Name")
                .withLastName("Owners Last Name")
                .withStreet("Owners Street")
                .withCity("Owners City");
    }

    public static long getEntityId(final Response added) {
        return Long.parseLong(
                URI.create(added.getHeaderString(HttpHeaders.LOCATION))
                        .getPath()
                        .split("/")[4]);
    }

}
