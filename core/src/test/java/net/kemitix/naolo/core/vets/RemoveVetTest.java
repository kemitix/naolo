package net.kemitix.naolo.core.vets;

import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.EntityRepository;
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
    private final EntityRepository<Veterinarian> repository;
    private final RemoveVet removeVet;

    public RemoveVetTest(@Mock final EntityRepository<Veterinarian> repository) {
        this.repository = repository;
        removeVet = new RemoveVet(repository);
    }

    @Test
    @DisplayName("Remove a Vet")
    public void removeVet() {
        //given
        final Veterinarian vet = new Veterinarian();
        given(repository.remove(id)).willReturn(Optional.of(vet));
        final var request= removeVet.request(id);
        //when
        final var response = removeVet.invoke(request);
        //then
        assertThat(response.getEntity()).contains(vet);
    }

    @Test
    @DisplayName("Remove a Vet that does not exist")
    public void removeMissingVet() {
        //given
        given(repository.remove(id)).willReturn(Optional.empty());
        final var request= removeVet.request(id);
        //when
        final var response = removeVet.invoke(request);
        //then
        assertThat(response.getEntity()).isEmpty();
    }
}
