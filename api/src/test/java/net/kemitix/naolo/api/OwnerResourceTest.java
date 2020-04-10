package net.kemitix.naolo.api;

import net.kemitix.naolo.core.owners.*;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.OwnerRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OwnerResourceTest
        implements WithAssertions {

    private final OwnerRepository repository = mock(OwnerRepository.class);
    private final OwnerResource resource =
            new OwnerResource(
                    new ListAllOwners(repository),
                    new AddOwner(repository),
                    new GetOwner(repository),
                    new UpdateOwner(repository),
                    new RemoveOwner(repository));

    @Test
    @DisplayName("get all owners - 200 ok")
    public void getAllOwners() {
        //given
        final List<Owner> owners = Arrays.asList(
                new Owner().withFirstName("bob"),
                new Owner
                        ().withFirstName("sam"));
        given(repository.findAll()).willReturn(owners.stream());
        //when
        final Response response = resource.allOwners();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
        assertThat(response.getEntity()).isEqualTo(owners);
    }

    @Test
    @DisplayName("Add an Owner")
    public void addOwner() {
        //given
        final Owner owner = new Owner();
        final long id = new Random().nextLong();
        given(repository.add(owner))
                .willReturn(owner.withId(id));
        //when
        final Response response = resource.add(owner);
        //then
        assertThat(response.getStatus())
                .isEqualTo(HttpServletResponse.SC_CREATED);
        assertThat(response.getLocation())
                .hasPath("/owners/" + id);
    }

    @Test
    @DisplayName("Get an Owner that exists")
    public void getExistingOwner() {
        //given
        final long id = new Random().nextLong();
        final Owner owner = new Owner().withId(id);
        given(repository.find(id))
                .willReturn(Optional.of(owner));
        //when
        final Response response = resource.getOwner(id);
        //then
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
        assertThat(response.getEntity()).isEqualTo(owner);
    }

    @Test
    @DisplayName("Get an Owner that does not exist")
    public void getMissingOwner() {
        //given
        final long id = new Random().nextLong();
        given(repository.find(id))
                .willReturn(Optional.empty());
        //when
        final Response response = resource.getOwner(id);
        //then
        assertThat(response.getStatus())
                .isEqualTo(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Update an Owner that exists")
    public void updateExistingOwner() {
        //given
        final long id = new Random().nextLong();
        final Owner owner = new Owner().withId(id);
        given(repository.update(owner))
                .willReturn(Optional.of(owner));
        //when
        final Response response = resource.update(id, owner);
        //then
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
        assertThat(response.getEntity()).isEqualTo(owner);
    }

    @Test
    @DisplayName("Update an Owner that does not exist")
    public void updateMissingOwner() {
        //given
        final long id = new Random().nextLong();
        final Owner owner = new Owner().withId(id);
        given(repository.update(owner))
                .willReturn(Optional.empty());
        //when
        final Response response = resource.update(id, owner);
        //then
        assertThat(response.getStatus())
                .isEqualTo(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Remove an Owner that exists")
    public void removeExistingOwner() {
        //given
        final long id = new Random().nextLong();
        final Owner owner = new Owner().withId(id);
        given(repository.remove(id)).willReturn(Optional.of(owner));
        //when
        final Response response = resource.remove(id);
        //then
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
    }

    @Test
    @DisplayName("Remove an Owner that does not exist")
    public void removeMissingOwner() {
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