package net.kemitix.naolo.pets;

import net.kemitix.naolo.core.jpa.EntityRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Random;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PetRepositoryTest
        implements WithAssertions {

    private final EntityManager entityManager;
    private final EntityRepository<Pet> repository;
    private final TypedQuery<Pet> allPetsQuery;
    private final Stream<Pet> allPetsStream;
    private final long id = new Random().nextLong();
    private final Pet managedPet= new Pet();
    private final Pet unmanagedPet = new Pet();

    public PetRepositoryTest(
            @Mock final EntityManager entityManager,
            @Mock final TypedQuery<Pet> allPetsQuery,
            @Mock final Stream<Pet> allPetsStream
    ) {
        this.entityManager = entityManager;
        repository = new PetRepository(entityManager);
        this.allPetsQuery = allPetsQuery;
        this.allPetsStream = allPetsStream;
    }

    @Test
    @DisplayName("Has a no-args constructor")
    public void hasNoArgsConstructor() {
        assertThatCode(PetRepository::new)
                .doesNotThrowAnyException();
    }


    @Test
    @DisplayName("List All invokes named query")
    public void listAll() {
        //given
        given(entityManager
                .createNamedQuery(Pet.FIND_ALL, Pet.class))
                .willReturn(allPetsQuery);
        given(allPetsQuery.getResultStream())
                .willReturn(allPetsStream);
        //when
        final var result = repository.findAll();
        //then
        assertThat(result).isSameAs(allPetsStream);
    }

    @Test
    @DisplayName("Adding a Pet")
    public void addPet() {
        //given
        given(entityManager.merge(unmanagedPet)).willReturn(managedPet);
        //when
        final var result = repository.add(unmanagedPet);
        //then
        assertThat(result).isSameAs(managedPet);
    }

    @Test
    @DisplayName("Getting a Pet")
    public void getPet() {
        //given
        given(entityManager.find(Pet.class, id))
                .willReturn(managedPet);
        //when
        final var result = repository.find(id);
        //then
        assertThat(result).contains(managedPet);
    }

    @Test
    @DisplayName("Update an Owner")
    public void updateOwner() {
        //given
        final Pet pet = unmanagedPet.withId(id);
        given(entityManager.find(Pet.class, id))
                .willReturn(managedPet);
        given(entityManager.merge(pet)).willReturn(managedPet);
        //when
        final var result = repository.update(pet);
        //then
        assertThat(result).contains(managedPet);
    }

    @Test
    @DisplayName("Remove a Pet")
    public void removePet() {
        //given
        final Pet pet = managedPet.withId(id);
        given(entityManager.find(Pet.class, id))
                .willReturn(pet);
        //when
        final var result = repository.remove(id);
        //then
        verify(entityManager).remove(pet);
        assertThat(result).contains(pet);
    }
}
