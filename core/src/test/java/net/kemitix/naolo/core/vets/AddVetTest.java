package net.kemitix.naolo.core.vets;

import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddVetTest
        implements WithAssertions {

    @Test
    @DisplayName("Add a Vet")
    public void addVets() {
        //given
        final VeterinarianRepository repository =
                mock(VeterinarianRepository.class);
        final AddVet addVet = new AddVet(repository);
        final String name = "name";
        final List<VetSpecialisation> specialisations =
                Arrays.asList(
                        VetSpecialisation.RADIOLOGY,
                        VetSpecialisation.SURGERY);
        final Veterinarian vet =
                new Veterinarian()
                        .withName(name)
                        .withSpecialisations(specialisations);
        final long nextId = 42;
        given(repository.add(vet))
                .willAnswer(call ->
                        ((Veterinarian) call.getArgument(0))
                                .withId(nextId));
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