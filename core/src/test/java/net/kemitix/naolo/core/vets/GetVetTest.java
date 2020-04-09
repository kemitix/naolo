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

public class GetVetTest
        implements WithAssertions {

    private final long id = new Random().nextLong();
    private final Veterinarian vet = new Veterinarian();
    @Mock
    private VeterinarianRepository repository;
    private GetVet useCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        useCase = new GetVet(repository);
    }

    @Test
    @DisplayName("Get a Vet that exists")
    public void getExistingVet() {
        //given
        given(repository.find(id))
                .willReturn(Optional.of(vet));
        //when
        final GetVet.Response result =
                useCase.invoke(GetVet.Request.builder().id(id).build());
        //then
        assertThat(result.getVeterinarian())
                .contains(vet);
    }

    @Test
    @DisplayName("Get a Vet that does not exist")
    public void getMissingVet() {
        //given
        given(repository.find(id))
                .willReturn(Optional.empty());
        //when
        final GetVet.Response result =
                useCase.invoke(GetVet.Request.builder().id(id).build());
        //then
        assertThat(result.getVeterinarian())
                .isEmpty();
    }
}