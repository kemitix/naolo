package net.kemitix.naolo.owners;

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
public class GetOwnerTest
        implements WithAssertions {

    private final long id = new Random().nextLong();
    private final Owner owner = new Owner();
    private final EntityRepository<Owner> repository;
    private final GetOwner getOwner;

    public GetOwnerTest(@Mock final EntityRepository<Owner> repository) {
        this.repository = repository;
        getOwner = new GetOwner(repository);
    }

    @Test
    @DisplayName("Get a Vet that exists")
    public void getExistingVet() {
        //given
        given(repository.find(id)).willReturn(Optional.of(owner));
        final var request = getOwner.request(id);
        //when
        final var response = getOwner.invoke(request);
        //then
        assertThat(response.getEntity()).contains(owner);
    }

    @Test
    @DisplayName("Get an Owner that does not exist")
    public void getMissingOwner() {
        //given
        given(repository.find(id)).willReturn(Optional.empty());
        final var request = getOwner.request(id);
        //when
        final var response = getOwner.invoke(request);
        //then
        assertThat(response.getEntity()).isEmpty();
    }
}