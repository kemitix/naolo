package net.kemitix.naolo.core.owners;

import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.OwnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UpdateOwnerTest {

    private final OwnerRepository repository = mock(OwnerRepository.class);
    private final UpdateOwner updateOwner = new UpdateOwner(repository);

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

    @Test
    @DisplayName("Update Owner")
    public void updateOwner() {
        //given
        given(repository.update(updatedOwner))
                .willReturn(Optional.of(updatedOwner));
        final UpdateOwner.Request request =
                UpdateOwner.Request.builder()
                        .owner(updatedOwner)
                        .build();
        //when
        final UpdateOwner.Response response = updateOwner.invoke(request);
        //then
        verify(repository).update(updatedOwner);
        assertThat(response.getOwner())
                .contains(updatedOwner);
    }
}