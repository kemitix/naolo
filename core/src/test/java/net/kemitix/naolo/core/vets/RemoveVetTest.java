package net.kemitix.naolo.core.vets;

import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;
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
public class RemoveVetTest
        implements WithAssertions {

    private final long id = new Random().nextLong();
    private final VeterinarianRepository repository;
    private final RemoveVet removeVet;

    public RemoveVetTest(@Mock final VeterinarianRepository repository) {
        this.repository = repository;
        removeVet = new RemoveVet(repository);
    }

    @Test
    @DisplayName("Remove a Vet")
    public void removeVet() {
        //given
        final RemoveVet.Request request=
                RemoveVet.Request.builder()
                        .id(id).build();
        final Veterinarian vet = new Veterinarian();
        final Optional<Veterinarian> foundVet =
                Optional.of(vet);
        given(repository.remove(id)).willReturn(foundVet);
        //when
        final RemoveVet.Response response =
                removeVet.invoke(request);
        //then
        assertThat(response.getVeterinarian())
                .contains(vet);
    }

    @Test
    @DisplayName("Remove a Vet that does not exist")
    public void removeMissingVet() {
        //given
        final RemoveVet.Request request=
                RemoveVet.Request.builder()
                        .id(id).build();
        given(repository.remove(id)).willReturn(Optional.empty());
        //when
        final RemoveVet.Response response =
                removeVet.invoke(request);
        //then
        assertThat(response.getVeterinarian())
                .isEmpty();
    }
}
