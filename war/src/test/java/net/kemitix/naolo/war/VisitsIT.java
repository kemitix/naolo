package net.kemitix.naolo.war;

import net.kemitix.naolo.OwnerResource;
import net.kemitix.naolo.PetResource;
import net.kemitix.naolo.VetResource;
import net.kemitix.naolo.VisitResource;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.entities.Visit;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import static net.kemitix.naolo.war.ObjectMother.getEntityId;

@MicroShedTest
@SharedContainerConfig(AppContainerConfig.class)
public class VisitsIT
        implements WithAssertions {

    @RESTClient
    public static VisitResource visitResource;

    @RESTClient
    public static OwnerResource ownerResource;

    @RESTClient
    public static PetResource petResource;

    @RESTClient
    public static VetResource vetResource;

    @Test
    @DisplayName("lifecycle")
    public void lifecycle() {
        // start with no visits
        assertThat(visitResource.all()).isEmpty();

        // add a visit
        // first - add an owner
        final Owner newOwner = ObjectMother.getNewOwner();
        final Response addOwnerResponse = ownerResource.add(newOwner);
        final long ownerId = getEntityId(addOwnerResponse);
        final Owner owner = newOwner.withId(ownerId);
        // then a pet
        final Pet newPet = ObjectMother.getNewPet(owner);
        final Response addPetResponse = petResource.add(newPet);
        final long petId = getEntityId(addPetResponse);
        final Pet pet = newPet.withId(petId);
        // then a vet
        final Veterinarian newVet = ObjectMother.getNewVet();
        final Response addVetResponse = vetResource.add(newVet);
        final long vetId = getEntityId(addVetResponse);
        final Veterinarian vet = newVet.withId(vetId);
        // now add the visit
        final Visit newVisit = ObjectMother.getNewVisit(pet, vet);
        final Response addVisitResponse = visitResource.add(newVisit);
        final long visitId = getEntityId(addVisitResponse);
        final Visit visit = newVisit.withId(visitId);

        // list all visits - we have one
        assertThat(visitResource.all()).containsExactly(visit);

        // get the visit
        assertThat(visitResource.get(visitId)).isEqualTo(visit);

        // update the visit
        final Visit updatedVisit = visit.withDescription("New Visit Description");
        assertThat(visitResource.update(visitId, updatedVisit))
                .isEqualTo(updatedVisit);

        // get the updated visit
        assertThat(visitResource.get(visitId)).isEqualTo(updatedVisit);

        // list all visits - we have the updated one
        assertThat(visitResource.all()).containsExactly(updatedVisit);

        // remove visit
        visitResource.remove(visitId);

        // clean up
        vetResource.remove(vetId);
        petResource.remove(petId);
        ownerResource.remove(ownerId);

        // get visits - not found
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> visitResource.get(visitId));

        // get all visits - none found
        assertThat(visitResource.all()).isEmpty();
    }
}
