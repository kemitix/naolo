package net.kemitix.naolo.core.owners;

import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.OwnerRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Random;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RemoveOwnerTest
        implements WithAssertions {

    private final OwnerRepository repository = mock(OwnerRepository.class);
    private final RemoveOwner removeOwner = new RemoveOwner(repository);
    private final long id = new Random().nextLong();

    @Test
    @DisplayName("Remove an Owner")
    public void removeOwner() {
        //given
        final RemoveOwner.Request request =
                RemoveOwner.Request.builder()
                        .id(id)
                        .build();
        final Owner owner = new Owner();
        given(repository.remove(id)).willReturn(Optional.of(owner));
        //when
        final RemoveOwner.Response response = removeOwner.invoke(request);
        //then
        verify(repository).remove(id);
        assertThat(response.getOwner()).contains(owner);
    }

    @Test
    @DisplayName("Remove an Owner that does not exist")
    public void removeMissingOwner() {
        //given
        final RemoveOwner.Request request =
                RemoveOwner.Request.builder()
                        .id(id)
                        .build();
        given(repository.remove(id)).willReturn(Optional.empty());
        //when
        final RemoveOwner.Response response = removeOwner.invoke(request);
        //then
        verify(repository).remove(id);
        assertThat(response.getOwner()).isEmpty();
    }
}
