package net.kemitix.naolo.core.vets;

import net.kemitix.naolo.core.GetEntityRequest;
import net.kemitix.naolo.core.GetEntityResponse;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.EntityRepository;
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
    private final GetVet useCase;

    public GetVetTest(@Mock final EntityRepository<Veterinarian> repository) {
        this.repository = repository;
        useCase = new GetVet(repository);
    }

    @Test
    @DisplayName("Get a Vet that exists")
    public void getExistingVet() {
        //given
        given(repository.find(id))
                .willReturn(Optional.of(vet));
        final GetEntityRequest<Veterinarian> request =
                GetVet.request(id);
        //when
        final GetEntityResponse<Veterinarian> result =
                useCase.invoke(request);
        //then
        assertThat(result.getEntity())
                .contains(vet);
    }

    @Test
    @DisplayName("Get a Vet that does not exist")
    public void getMissingVet() {
        //given
        given(repository.find(id))
                .willReturn(Optional.empty());
        final var request = GetVet.request(id);
        //when
        final var result = useCase.invoke(request);
        //then
        assertThat(result.getEntity()).isEmpty();
    }
}