package net.kemitix.naolo.plugin.visits;

import net.kemitix.naolo.core.StreamZipper;
import net.kemitix.naolo.core.Tuple;
import net.kemitix.naolo.storage.EntityRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ListVisitTest implements WithAssertions {

    private final EntityRepository<Visit> repository;
    private final ListVisit listVets;

    public ListVisitTest(@Mock final EntityRepository<Visit> repository) {
        this.repository = repository;
        listVets = new ListVisit(repository);
    }

    @Test
    @DisplayName("List all Visits")
    public void listAll() {
        //given
        final List<Visit> visits = Arrays.asList(
                new Visit().withId(42L),
                new Visit().withId(22L));
        given(repository.findAll()).willReturn(visits.stream());
        final var request = listVets.request();
        //when
        final var response = listVets.invoke(request);
        //then
        final Stream<Tuple<Visit, Visit>> zipped =
                StreamZipper.zip(visits, response.getEntities(), Tuple::of);
        assertThat(zipped)
                .hasSize(visits.size())
                .allSatisfy(t -> {
                    final Visit expected = t.get1();
                    final Visit result = t.get2();
                    assertThat(expected).isEqualTo(result);
                });
    }
}