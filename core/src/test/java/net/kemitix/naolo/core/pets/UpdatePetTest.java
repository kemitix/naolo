package net.kemitix.naolo.core.pets;

import net.kemitix.naolo.entities.Pet;
import net.kemitix.naolo.storage.spi.EntityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UpdatePetTest {

    private final EntityRepository<Pet> repository;
    private final UpdatePet updatePet;

    private final Pet originalPet =
            new Pet()
                    .withId(43L)
                    .withName("original name");
    private final Pet updatedPet =
            new Pet()
                    .withId(43L)
                    .withName("updated name");

    public UpdatePetTest(@Mock final EntityRepository<Pet> repository) {
        this.repository = repository;
        updatePet = new UpdatePet(repository);
    }

    @Test
    @DisplayName("Update Owner")
    public void updateOwner() {
        //given
        given(repository.update(updatedPet))
                .willReturn(Optional.of(updatedPet));
        final var request = updatePet.request(updatedPet);
        //when
        final var response = updatePet.invoke(request);
        //then
        assertThat(response.getEntity()).contains(updatedPet);
        verify(repository).update(updatedPet);
    }
}