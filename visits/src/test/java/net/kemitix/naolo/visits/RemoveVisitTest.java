package net.kemitix.naolo.visits;

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
public class RemoveVisitTest
        implements WithAssertions {

    private final long id = new Random().nextLong();
    private final EntityRepository<Visit> repository;
    private final RemoveVisit removeVet;

    public RemoveVisitTest(@Mock final EntityRepository<Visit> repository) {
        this.repository = repository;
        removeVet = new RemoveVisit(repository);
    }

    @Test
    @DisplayName("Remove a Visit")
    public void removeVisit() {
        //given
        final Visit visit = new Visit();
        given(repository.remove(id)).willReturn(Optional.of(visit));
        final var request= removeVet.request(id);
        //when
        final var response = removeVet.invoke(request);
        //then
        assertThat(response.getEntity()).contains(visit);
    }

    @Test
    @DisplayName("Remove a Visit that does not exist")
    public void removeMissingVisit() {
        //given
        given(repository.remove(id)).willReturn(Optional.empty());
        final var request= removeVet.request(id);
        //when
        final var response = removeVet.invoke(request);
        //then
        assertThat(response.getEntity()).isEmpty();
    }
}
