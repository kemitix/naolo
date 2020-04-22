package net.kemitix.naolo.api;

import net.kemitix.naolo.core.pets.*;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.storage.EntityRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PetResourceTest
        implements WithAssertions {

    private final EntityRepository<Pet> repository;
    private final PetResource resource;

    public PetResourceTest(@Mock final EntityRepository<Pet> repository) {
        this.repository = repository;
        resource =
                new PetResource(
                        new ListPets(repository),
                        new AddPet(repository),
                        new GetPet(repository),
                        new UpdatePet(repository),
                        new RemovePet(repository)
                );
    }

    @Test
    @DisplayName("Has a no-args constructor")
    public void hasNoArgsConstructor() {
        assertThatCode(PetResource::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("get all pets - 200 ok")
    public void getAllPets() {
        //given
        final List<Pet> pets = Arrays.asList(
                new Pet().withName("rover"),
                new Pet().withName("dougal"));
        given(repository.findAll()).willReturn(pets.stream());
        //when
        final List<Pet> response = resource.all();
        //then
        assertThat(response).isEqualTo(pets);
    }

    @Test
    @DisplayName("Add a Pet")
    public void addPet() {
        //given
        final Pet pet = new Pet();
        final long id = new Random().nextLong();
        given(repository.add(pet))
                .willReturn(pet.withId(id));
        //when
        final Response response = resource.add(pet);
        //then
        assertThat(response.getStatus())
                .isEqualTo(HttpServletResponse.SC_CREATED);
        assertThat(response.getLocation())
                .hasPath("/pets/" + id);
    }

    @Test
    @DisplayName("Get a Pet that exists")
    public void getExistingPet() {
        //given
        final long id = new Random().nextLong();
        final Pet pet = new Pet().withId(id);
        given(repository.find(id)).willReturn(Optional.of(pet));
        //when
        final Pet response = resource.get(id);
        //then
        assertThat(response).isEqualTo(pet);
    }

    @Test
    @DisplayName("Get a Pet that does not exist")
    public void getMissingPet() {
        //given
        final long id = new Random().nextLong();
        given(repository.find(id))
                .willReturn(Optional.empty());
        //then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() ->
                        resource.get(id));
    }

    @Test
    @DisplayName("Update a Pet that exists")
    public void updateExistingPet() {
        //given
        final long id = new Random().nextLong();
        final Pet pet = new Pet().withId(id);
        given(repository.update(pet))
                .willReturn(Optional.of(pet));
        //when
        final Pet response = resource.update(id, pet);
        //then
        assertThat(response).isEqualTo(pet);
    }

    @Test
    @DisplayName("Update a Pet that does not exist")
    public void updateMissingPet() {
        //given
        final long id = new Random().nextLong();
        final Pet pet = new Pet().withId(id);
        given(repository.update(pet))
                .willReturn(Optional.empty());
        //then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() ->
                        resource.update(id, pet));
    }

    @Test
    @DisplayName("Remove a Pet that exists")
    public void removeExistingPet() {
        //given
        final long id = new Random().nextLong();
        final Pet pet = new Pet().withId(id);
        given(repository.remove(id)).willReturn(Optional.of(pet));
        //when
        final Pet response = resource.remove(id);
        //then
        assertThat(response).isEqualTo(pet);
    }

    @Test
    @DisplayName("Remove a Pet that does not exist")
    public void removeMissingPet() {
        //given
        final long id = new Random().nextLong();
        given(repository.remove(id)).willReturn(Optional.empty());
        //then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() ->
                        resource.remove(id));
    }

}