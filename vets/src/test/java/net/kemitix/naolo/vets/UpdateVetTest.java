package net.kemitix.naolo.vets;

import net.kemitix.naolo.storage.EntityRepository;
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

    private final EntityRepository<Veterinarian> repository;
    private final UpdateVet updateVet;

    public UpdateVetTest(@Mock final EntityRepository<Veterinarian> repository) {
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
        final var request = updateVet.request(updatedVet);
        final var response = updateVet.invoke(request);
        //then
        assertThat(response.getEntity()).contains(expectedVet);
        verify(repository).update(expectedVet);
    }
}
