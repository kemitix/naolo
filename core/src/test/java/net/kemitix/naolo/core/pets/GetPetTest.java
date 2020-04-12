package net.kemitix.naolo.core.pets;

import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.storage.spi.EntityRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GetPetTest
        implements WithAssertions {

    private final long id = new Random().nextLong();
    private final Pet pet = new Pet();
    private final EntityRepository<Pet> repository;
    private final GetPet useCase;

    public GetPetTest(@Mock final EntityRepository<Pet> repository) {
        this.repository = repository;
        useCase = new GetPet(repository);
    }

    @Test
    @DisplayName("Get a Pet that exists")
    public void getExistingPet() {
        //given
        given(repository.find(id)).willReturn(Optional.of(pet));
        final var request = GetPet.request(id);
        //when
        final var response = useCase.invoke(request);
        //then
        assertThat(response.getEntity()).contains(pet);
    }

    @Test
    @DisplayName("Get a Vet that does not exist")
    public void getMissingVet() {
        //given
        given(repository.find(id)).willReturn(Optional.empty());
        final var request = GetPet.request(id);
        //when
        final var response = useCase.invoke(request);
        //then
        assertThat(response.getEntity()).isEmpty();
    }
}