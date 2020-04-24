package net.kemitix.naolo.owners;

import net.kemitix.naolo.storage.EntityRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UpdateOwnerTest
        implements WithAssertions {

    private final EntityRepository<Owner> repository;
    private final UpdateOwner updateOwner;

    private final Owner originalOwner =
            new Owner()
                    .withId(43L)
                    .withFirstName("original first name")
                    .withLastName("original last name")
                    .withStreet("original street")
                    .withCity("original city");
    private final Owner updatedOwner =
            new Owner()
                    .withId(43L)
                    .withFirstName("updated first name")
                    .withLastName("updated last name")
                    .withStreet("updated street")
                    .withCity("updated city");

    public UpdateOwnerTest(@Mock final EntityRepository<Owner> repository) {
        this.repository = repository;
        updateOwner = new UpdateOwner(repository);
    }

    @Test
    @DisplayName("Update Owner")
    public void updateOwner() {
        //given
        given(repository.update(updatedOwner))
                .willReturn(Optional.of(updatedOwner));
        final var request = updateOwner.request(updatedOwner);
        //when
        final var response = updateOwner.invoke(request);
        //then
        assertThat(response.getEntity()).contains(updatedOwner);
        verify(repository).update(updatedOwner);
    }
}