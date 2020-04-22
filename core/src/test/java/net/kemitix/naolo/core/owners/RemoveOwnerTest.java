package net.kemitix.naolo.core.owners;

import net.kemitix.naolo.entities.Owner;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RemoveOwnerTest
        implements WithAssertions {

    private final EntityRepository<Owner> repository;
    private final RemoveOwner removeOwner;
    private final long id = new Random().nextLong();

    public RemoveOwnerTest(@Mock final EntityRepository<Owner> repository) {
        this.repository = repository;
        removeOwner = new RemoveOwner(repository);
    }

    @Test
    @DisplayName("Remove an Owner")
    public void removeOwner() {
        //given
        final Owner owner = new Owner();
        given(repository.remove(id)).willReturn(Optional.of(owner));
        final var request = removeOwner.request(id);
        //when
        final var response = removeOwner.invoke(request);
        //then
        assertThat(response.getEntity()).contains(owner);
        verify(repository).remove(id);
    }

    @Test
    @DisplayName("Remove an Owner that does not exist")
    public void removeMissingOwner() {
        //given
        final var request = removeOwner.request(id);
        given(repository.remove(id)).willReturn(Optional.empty());
        //when
        final var response = removeOwner.invoke(request);
        //then
        assertThat(response.getEntity()).isEmpty();
        verify(repository).remove(id);
    }
}
