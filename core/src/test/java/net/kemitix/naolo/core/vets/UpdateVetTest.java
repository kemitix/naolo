package net.kemitix.naolo.core.vets;

import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UpdateVetTest
        implements WithAssertions {

    private final Veterinarian originalVet =
            new Veterinarian()
                    .withId(23L)
                    .withName("original name")
                    .withSpecialisations(Collections.singletonList(
                            VetSpecialisation.RADIOLOGY));
    private final Veterinarian expectedVet =
            new Veterinarian()
                    .withId(23L)
                    .withName("update name")
                    .withSpecialisations(Arrays.asList(
                            VetSpecialisation.DENTISTRY,
                            VetSpecialisation.SURGERY));

    private final VeterinarianRepository repository;
    private final UpdateVet updateVet;

    public UpdateVetTest(@Mock final VeterinarianRepository repository) {
        this.repository = repository;
        updateVet = new UpdateVet(repository);
    }

    @Test
    @DisplayName("Can update Vet")
    public void canUpdateVet() {
        //given
        given(repository.update(expectedVet))
                .willReturn(Optional.of(expectedVet));
        //when
        final Veterinarian updatedVet = originalVet
                .withName(expectedVet.getName())
                .withSpecialisations(expectedVet.getSpecialisations());
        final UpdateVet.Response response =
                updateVet.invoke(
                        UpdateVet.Request.builder()
                                .veterinarian(updatedVet)
                                .build());
        //then
        verify(repository).update(expectedVet);
        assertThat(response.getVeterinarian())
                .contains(expectedVet);
    }
}
