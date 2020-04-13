package net.kemitix.naolo.core.visits;

import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;
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
public class UpdateVisitTest
        implements WithAssertions {

    private final Visit originalVisit =
            new Visit()
                    .withId(23L)
                    .withDescription("original description");
    private final Visit expectedVisit =
            new Visit()
                    .withId(23L)
                    .withDescription("updated description");

    private final EntityRepository<Visit> repository;
    private final UpdateVisit updateVisit;

    public UpdateVisitTest(@Mock final EntityRepository<Visit> repository) {
        this.repository = repository;
        updateVisit = new UpdateVisit(repository);
    }

    @Test
    @DisplayName("Can update Visit")
    public void canUpdateVisit() {
        //given
        final Visit updatedVisit = originalVisit
                .withDescription("original description");
        given(repository.update(updatedVisit))
                .willReturn(Optional.of(expectedVisit));
        //when
        final var request = updateVisit.request(updatedVisit);
        final var response = updateVisit.invoke(request);
        //then
        assertThat(response.getEntity()).contains(expectedVisit);
        verify(repository).update(updatedVisit);
    }
}
