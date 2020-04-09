package net.kemitix.naolo.core.vets;

import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class AddVetTest
implements WithAssertions {

    private final Long nextId = new Random().nextLong();
    @Mock
    private VeterinarianRepository repository;
    private AddVet addVet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        addVet = new AddVet(repository);

        given(repository.add(any()))
                .willAnswer(call ->
                        ((Veterinarian) call.getArgument(0))
                                .withId(nextId));
    }

    @Test
    @DisplayName("When invoked the vet is added")
    public void invokeAddsVet() {
        //given
        final Veterinarian vet =
                new Veterinarian()
                        .withName("name")
                        .withSpecialisations(Collections.singleton(
                                VetSpecialisation.RADIOLOGY));
        final AddVet.Request request =
                AddVet.Request.builder()
                        .veterinarian(vet)
                        .build();
        //when
        final AddVet.Response response = addVet.invoke(request);
        //then
        assertThat(response.getVeterinarian().getId()).isEqualTo(nextId);
        verify(repository).add(vet);
    }
}