package net.kemitix.naolo.core.visits;

import net.kemitix.naolo.entities.Visit;
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
public class GetVisitTest
        implements WithAssertions {

    private final long id = new Random().nextLong();
    private final Visit visit = new Visit();
    private final EntityRepository<Visit> repository;
    private final GetVisit getVisit;

    public GetVisitTest(@Mock final EntityRepository<Visit> repository) {
        this.repository = repository;
        getVisit = new GetVisit(repository);
    }

    @Test
    @DisplayName("Get a Visit that exists")
    public void getExistingVisit() {
        //given
        given(repository.find(id)).willReturn(Optional.of(visit));
        final var request = getVisit.request(id);
        //when
        final var result = getVisit.invoke(request);
        //then
        assertThat(result.getEntity()).contains(visit);
    }

    @Test
    @DisplayName("Get a Visit that does not exist")
    public void getMissingVisit() {
        //given
        given(repository.find(id)).willReturn(Optional.empty());
        final var request = getVisit.request(id);
        //when
        final var result = getVisit.invoke(request);
        //then
        assertThat(result.getEntity()).isEmpty();
    }
}