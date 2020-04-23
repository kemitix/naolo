package net.kemitix.naolo.plugin.visits;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Random;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VisitRepositoryTest
        implements WithAssertions {

    private final EntityManager entityManager;
    private final VisitRepository repository;
    private final TypedQuery<Visit> allVisitsQuery;
    private final Stream<Visit> allVisitsStream;
    private final long id = new Random().nextLong();
    private final Visit managedVisit = new Visit();
    private final Visit unmanagedVisit = new Visit();

    public VisitRepositoryTest(
            @Mock final EntityManager entityManager,
            @Mock final TypedQuery<Visit> allVisitsQuery,
            @Mock final Stream<Visit> allVisitsStream
    ) {
        this.entityManager = entityManager;
        repository = new VisitRepository(entityManager);
        this.allVisitsQuery = allVisitsQuery;
        this.allVisitsStream = allVisitsStream;
    }

    @Test
    @DisplayName("Has a no-args constructor")
    public void hasNoArgsConstructor() {
        assertThatCode(VisitRepository::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("List All invokes named query")
    public void listAll() {
        //given
        given(entityManager
                .createNamedQuery(Visit.FIND_ALL, Visit.class))
                .willReturn(allVisitsQuery);
        given(allVisitsQuery.getResultStream())
                .willReturn(allVisitsStream);
        //when
        final var result = repository.findAll();
        //then
        assertThat(result).isSameAs(allVisitsStream);
    }

    @Test
    @DisplayName("Adding a Visit")
    public void addVist() {
        //given
        given(entityManager.merge(unmanagedVisit)).willReturn(managedVisit);
        //when
        final var result = repository.add(unmanagedVisit);
        //then
        assertThat(result).isSameAs(managedVisit);
    }

    @Test
    @DisplayName("Getting a Visit")
    public void getVisit() {
        //given
        given(entityManager.find(Visit.class, id))
                .willReturn(managedVisit);
        //when
        final var result = repository.find(id);
        //then
        assertThat(result).contains(managedVisit);
    }

    @Test
    @DisplayName("Update a Visit")
    public void updateOwner() {
        //given
        final Visit visit = unmanagedVisit.withId(id);
        given(entityManager.find(Visit.class, id))
                .willReturn(managedVisit);
        given(entityManager.merge(visit)).willReturn(managedVisit);
        //when
        final var result = repository.update(visit);
        //then
        assertThat(result).contains(managedVisit);
    }

    @Test
    @DisplayName("Remove a Visit")
    public void removeVisit() {
        //given
        final Visit visit = managedVisit.withId(id);
        given(entityManager.find(Visit.class, id))
                .willReturn(visit);
        //when
        final var result = repository.remove(id);
        //then
        verify(entityManager).remove(visit);
        assertThat(result).contains(visit);
    }
}
