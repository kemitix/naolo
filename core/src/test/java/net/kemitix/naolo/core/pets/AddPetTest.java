package net.kemitix.naolo.core.pets;

import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.entities.PetType;
import net.kemitix.naolo.storage.spi.EntityRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.Random;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AddPetTest
        implements WithAssertions {

    private final EntityRepository<Pet> repository;
    private final AddPet addPet;

    public AddPetTest(@Mock final EntityRepository<Pet> repository) {
        this.repository = repository;
        addPet = new AddPet(repository);
    }

    @Test
    @DisplayName("Add a Pet")
    public void addPet() {
        //given
        final String name = "Pet's name";
        final ZonedDateTime dateOfBirth = ZonedDateTime.now();
        final PetType type = PetType.DOG;
        final Owner owner = new Owner();
        final Pet pet = new Pet()
                .withName(name)
                .withDateOfBirth(dateOfBirth)
                .withType(type)
                .withOwner(owner);
        final long nextId = new Random().nextLong();
        given(repository.add(pet)).willReturn(pet.withId(nextId));
        final var request = AddPet.request(pet);
        //when
        final var response = addPet.invoke(request);
        //then
        assertThat(response.getEntity().getId())
                .isEqualTo(nextId);
    }
}