package net.kemitix.naolo.core.pets;

import net.kemitix.naolo.core.StreamZipper;
import net.kemitix.naolo.core.Tuple;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.entities.PetType;
import net.kemitix.naolo.storage.EntityRepository;
import net.kemitix.naolo.storage.ListEntityRequest;
import net.kemitix.naolo.storage.ListEntityResponse;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.YEARS;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ListPetsTest
        implements WithAssertions {

    private final EntityRepository<Pet> repository;
    private final ListPets listPets;

    public ListPetsTest(@Mock final EntityRepository<Pet> repository) {
        this.repository = repository;
        listPets = new ListPets(repository);
    }

    @Test
    @DisplayName("List Pets")
    public void listPets() {
        //given
        final List<Pet> pets = Arrays.asList(
                new Pet().withId(43L).withName("rover")
                        .withDateOfBirth(
                                LocalDate.now()
                                        .minus(4, YEARS))
                        .withType(PetType.DOG)
                        .withOwner(new Owner()),
                new Pet().withId(23L).withName("dougal")
                        .withDateOfBirth(
                                LocalDate.now()
                                        .minus(5, YEARS))
                        .withType(PetType.CAT)
                        .withOwner(new Owner())
        );
        given(repository.findAll()).willReturn(pets.stream());
        final ListEntityRequest<Pet> request = listPets.request();
        //when
        final ListEntityResponse<Pet> response = listPets.invoke(request);
        //then
        final Stream<Tuple<Pet, Pet>> zipped =
                StreamZipper.zip(pets, response.getEntities(), Tuple::of);
        assertThat(zipped)
                .hasSize(pets.size())
                .allSatisfy(t -> {
                    final Pet expected = t.get1();
                    final Pet result = t.get2();
                    assertThat(expected).isEqualTo(result);
                });
    }
}