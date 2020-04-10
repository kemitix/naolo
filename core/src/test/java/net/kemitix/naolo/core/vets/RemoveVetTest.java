package net.kemitix.naolo.core.vets;

import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Random;

import static org.mockito.BDDMockito.given;

public class RemoveVetTest
        implements WithAssertions {

    private final long id = new Random().nextLong();
    @Mock
    private VeterinarianRepository repository;
    private RemoveVet removeVet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
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
