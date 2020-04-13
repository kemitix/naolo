package net.kemitix.naolo.api;

import net.kemitix.naolo.core.visits.*;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class VisitResourceTest
        implements WithAssertions {

    private final EntityRepository<Visit> repository;
    private final VisitResource resource;

    public VisitResourceTest(@Mock final EntityRepository<Visit> repository) {
        this.repository = repository;
        resource =
                new VisitResource(
                        new ListVisit(repository),
                        new AddVisit(repository),
                        new GetVisit(repository),
                        new UpdateVisit(repository),
                        new RemoveVisit(repository)
                );
    }

    @Test
    @DisplayName("get all visits - 200 ok")
    public void getAllVisits() {
        //given
        final List<Visit> pets = Arrays.asList(
                new Visit(),
                new Visit());
        given(repository.findAll()).willReturn(pets.stream());
        //when
        final Response response = resource.all();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
        assertThat(response.getEntity()).isEqualTo(pets);
    }

    @Test
    @DisplayName("Add a Pet")
    public void addPet() {
        //given
        final Visit visit = new Visit();
        final long id = new Random().nextLong();
        given(repository.add(visit))
                .willReturn(visit.withId(id));
        //when
        final Response response = resource.add(visit);
        //then
        assertThat(response.getStatus())
                .isEqualTo(HttpServletResponse.SC_CREATED);
        assertThat(response.getLocation())
                .hasPath("/visits/" + id);
    }

    @Test
    @DisplayName("Get a Visit that exists")
    public void getExistingVisit() {
        //given
        final long id = new Random().nextLong();
        final Visit visit = new Visit().withId(id);
        given(repository.find(id)).willReturn(Optional.of(visit));
        //when
        final Response response = resource.get(id);
        //then
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
        assertThat(response.getEntity()).isEqualTo(visit);
    }

    @Test
    @DisplayName("Get a Visit that does not exist")
    public void getMissingVisit() {
        //given
        final long id = new Random().nextLong();
        given(repository.find(id))
                .willReturn(Optional.empty());
        //when
        final Response response = resource.get(id);
        //then
        assertThat(response.getStatus())
                .isEqualTo(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Update a Visit that exists")
    public void updateExistingVisit() {
        //given
        final long id = new Random().nextLong();
        final Visit visit = new Visit().withId(id);
        given(repository.update(visit))
                .willReturn(Optional.of(visit));
        //when
        final Response response = resource.update(id, visit);
        //then
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
        assertThat(response.getEntity()).isEqualTo(visit);
    }

    @Test
    @DisplayName("Update a Visit that does not exist")
    public void updateMissingVisit() {
        //given
        final long id = new Random().nextLong();
        final Visit visit = new Visit().withId(id);
        given(repository.update(visit))
                .willReturn(Optional.empty());
        //when
        final Response response = resource.update(id, visit);
        //then
        assertThat(response.getStatus())
                .isEqualTo(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Remove a Visit that exists")
    public void removeExistingVisit() {
        //given
        final long id = new Random().nextLong();
        final Visit pet = new Visit().withId(id);
        given(repository.remove(id)).willReturn(Optional.of(pet));
        //when
        final Response response = resource.remove(id);
        //then
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
    }

    @Test
    @DisplayName("Remove a Visit that does not exist")
    public void removeMissingVisit() {
        //given
        final long id = new Random().nextLong();
        given(repository.remove(id)).willReturn(Optional.empty());
        //when
        final Response response = resource.remove(id);
        //then
        assertThat(response.getStatus())
                .isEqualTo(HttpServletResponse.SC_NOT_FOUND);
    }

}