package net.kemitix.naolo.core.vets;

import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.EntityRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddVetTest
        implements WithAssertions {

    private final EntityRepository<Veterinarian> repository;
    private final AddVet addVet;

    public AddVetTest(@Mock final EntityRepository<Veterinarian> repository) {
        this.repository = repository;
        addVet = new AddVet(repository);
    }

    @Test
    @DisplayName("Add a Vet")
    public void addVets() {
        //given
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
        given(repository.add(vet)).willReturn(vet.withId(nextId));
        final var request = addVet.request(vet);
        //when
        final var response = addVet.invoke(request);
        //then
        assertThat(response.getEntity().getId()).isEqualTo(nextId);
        verify(repository).add(vet);
    }
}