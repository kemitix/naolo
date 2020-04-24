package net.kemitix.naolo.war;

import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.pets.Pet;
import net.kemitix.naolo.pets.PetType;
import net.kemitix.naolo.plugin.visits.Visit;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

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
                        .split("/")[3]);
    }

    public static Pet getNewPet(final Owner owner) {
        return new Pet()
                .withId(0)
                .withName("Pets Name")
                .withDateOfBirth(LocalDate.of(2020, 3, 24))
                .withType(PetType.DOG)
                .withOwner(owner);
    }

    public static Veterinarian getNewVet() {
        return new Veterinarian()
                .withId(0)
                .withName("Vets Name")
                .withSpecialisations(Collections.singletonList(
                        VetSpecialisation.SURGERY
                ));
    }

    public static Visit getNewVisit(final Pet pet, final Veterinarian vet) {
        return new Visit()
                .withId(0)
                .withDescription("Visit Description")
                .withDateTime(LocalDateTime.now()
                        .truncatedTo(ChronoUnit.MINUTES))
                .withPet(pet)
                .withVeterinarian(vet);
    }
}
