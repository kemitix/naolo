package net.kemitix.naolo.vets;

import net.kemitix.naolo.core.jpa.EntityRepository;
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
public class GetVetTest
        implements WithAssertions {

    private final long id = new Random().nextLong();
    private final Veterinarian vet = new Veterinarian();
    private final EntityRepository<Veterinarian> repository;
    private final GetVet getVet;

    public GetVetTest(@Mock final EntityRepository<Veterinarian> repository) {
        this.repository = repository;
        getVet = new GetVet(repository);
    }

    @Test
    @DisplayName("Get a Vet that exists")
    public void getExistingVet() {
        //given
        given(repository.find(id)).willReturn(Optional.of(vet));
        final var request = getVet.request(id);
        //when
        final var result = getVet.invoke(request);
        //then
        assertThat(result.getEntity()).contains(vet);
    }

    @Test
    @DisplayName("Get a Vet that does not exist")
    public void getMissingVet() {
        //given
        given(repository.find(id)).willReturn(Optional.empty());
        final var request = getVet.request(id);
        //when
        final var result = getVet.invoke(request);
        //then
        assertThat(result.getEntity()).isEmpty();
    }
}