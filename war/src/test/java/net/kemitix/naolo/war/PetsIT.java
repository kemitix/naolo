package net.kemitix.naolo.war;

import net.kemitix.naolo.api.OwnerResource;
import net.kemitix.naolo.api.PetResource;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.entities.Pet;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.junit.jupiter.Container;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@MicroShedTest
public class PetsIT
        implements WithAssertions {
    @Container
    public static ApplicationContainer app =
            new ApplicationContainer()
                    .withAppContextRoot("/naolo-war-DEV-SNAPSHOT")
                    .withReadinessPath("/naolo-war-DEV-SNAPSHOT/naolo/pets");

    @RESTClient
    public static PetResource petResource;

    @RESTClient
    public static OwnerResource ownerResource;

    @Test
    @DisplayName("lifecycle")
    public void lifeycycle() {
        // start with no pets
        assertThat(petResource.all()).isEmpty();

        // add a pet

        // first - add the owner
        final Owner newOwner = ObjectMother.getNewOwner();
        final Response addOwnerResponse = ownerResource.add(newOwner);
        final long ownerId = ObjectMother.getEntityId(addOwnerResponse);

        // now add the pet
        final Pet newPet = ObjectMother.getNewPet(newOwner.withId(ownerId));
        final Response addPetResponse = petResource.add(newPet);

        final long petId = ObjectMother.getEntityId(addPetResponse);
        final Pet addedPet = newPet.withId(petId);

        // get the pat
        assertThat(petResource.get(petId)).isEqualTo(addedPet);

        // list all pets - we have one
        assertThat(petResource.all()).containsExactly(addedPet);

        // update the pet
        final Pet updatedPet = addedPet.withName("Pets new name");
        final Pet updatedPetReturned = petResource.update(petId, updatedPet);
        assertThat(updatedPetReturned).isEqualTo(updatedPet);

        // get the updated pet
        assertThat(petResource.get(petId)).isEqualTo(updatedPet);

        // list all pets - has the updated one
        assertThat(petResource.all()).containsExactly(updatedPet);

        // remove pet
        assertThat(petResource.remove(petId)).isEqualTo(updatedPet);

        // remove owner
        ownerResource.remove(ownerId);

        // get pet - not found
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> petResource.get(petId));

        // list all pets - none found
        assertThat(petResource.all()).isEmpty();
    }
}
