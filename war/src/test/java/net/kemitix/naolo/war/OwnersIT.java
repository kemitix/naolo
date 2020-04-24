package net.kemitix.naolo.war;

import net.kemitix.naolo.owners.Owner;
import net.kemitix.naolo.owners.OwnerResource;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@MicroShedTest
@SharedContainerConfig(AppContainerConfig.class)
public class OwnersIT
        implements WithAssertions {

    @RESTClient
    public static OwnerResource ownerResource;

    @Test
    @DisplayName("lifecycle")
    public void lifecycle() {
        // start with no Owners
        assertThat(ownerResource.all()).isEmpty();

        // add an Owner
        final Owner newOwner = ObjectMother.getNewOwner();
        final Response added = ownerResource.add(newOwner);
        assertThat(added.getStatus()).isEqualTo(HttpServletResponse.SC_CREATED);

        final long ownerId = ObjectMother.getEntityId(added);
        final Owner addedOwner = newOwner.withId(ownerId);

        // list all owners - we have one
        assertThat(ownerResource.all()).containsExactly(addedOwner);

        // get the owner
        assertThat(ownerResource.get(ownerId)).isEqualTo(addedOwner);

        // update the owner
        final Owner updatedOwner = addedOwner.withStreet("New Street");
        assertThat(ownerResource.update(ownerId, updatedOwner))
                .isEqualTo(updatedOwner);

        // get the updated owner
        assertThat(ownerResource.get(ownerId)).isEqualTo(updatedOwner);

        // list all owners - has the updated one
        assertThat(ownerResource.all()).containsExactly(updatedOwner);

        // remove owner
        ownerResource.remove(ownerId);

        // get owner - not found
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() ->
                        ownerResource.get(ownerId));

        // list all owners - none found
        assertThat(ownerResource.all()).isEmpty();
    }

}
