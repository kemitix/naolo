package net.kemitix.naolo.war;

import net.kemitix.naolo.vets.VetResource;
import net.kemitix.naolo.vets.Veterinarian;
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
public class VetsIT
        implements WithAssertions {

    @RESTClient
    public static VetResource vetResource;

    @Test
    @DisplayName("lifecycle")
    public void lifecycle() {
        // start with no vets
        assertThat(vetResource.all()).isEmpty();

        // add a vet
        final Veterinarian newVet = ObjectMother.getNewVet();
        final Response addedResponse = vetResource.add(newVet);
        assertThat(addedResponse.getStatus()).isEqualTo(HttpServletResponse.SC_CREATED);

        final long vetId = ObjectMother.getEntityId(addedResponse);
        final Veterinarian addedVet = newVet.withId(vetId);

        // get the pet
        assertThat(vetResource.get(vetId)).isEqualTo(addedVet);

        // list all pets - we have one
        assertThat(vetResource.all()).containsExactly(addedVet);

        // update the pet
        final Veterinarian updatedVet = addedVet.withName("Net Vet Name");
        assertThat(vetResource.update(vetId, updatedVet))
                .isEqualTo(updatedVet);

        // get the updated pet
        assertThat(vetResource.get(vetId)).isEqualTo(updatedVet);

        // list all pets - has the updated one
        assertThat(vetResource.all()).containsExactly(updatedVet);

        // remove pet
        vetResource.remove(vetId);

        // pet pet - not found
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> vetResource.get(vetId));

        // list all pets - none found
        assertThat(vetResource.all()).isEmpty();
    }
}
