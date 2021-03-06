package net.kemitix.naolo.owners;

import net.kemitix.naolo.core.jpa.EntityRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class OwnerResourceTest
        implements WithAssertions {

    private final EntityRepository<Owner> repository;
    private final OwnerResource resource;

    public OwnerResourceTest(@Mock final EntityRepository<Owner> repository) {
        this.repository = repository;
        resource =
                new OwnerResource(
                        new ListOwners(repository),
                        new AddOwner(repository),
                        new GetOwner(repository),
                        new UpdateOwner(repository),
                        new RemoveOwner(repository));
    }

    @Test
    @DisplayName("Has a no-args constructor")
    public void hasNoArgsConstructor() {
        assertThatCode(OwnerResource::new)
                .doesNotThrowAnyException();
    }

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
        final List<Owner> response = resource.all();
        //then
        assertThat(response).isEqualTo(owners);
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
        final Owner response = resource.get(id);
        //then
        assertThat(response).isEqualTo(owner);
    }

    @Test
    @DisplayName("Get an Owner that does not exist")
    public void getMissingOwner() {
        //given
        final long id = new Random().nextLong();
        given(repository.find(id))
                .willReturn(Optional.empty());
        //then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() ->
                        resource.get(id));
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
        final Owner response = resource.update(id, owner);
        //then
        assertThat(response).isEqualTo(owner);
    }

    @Test
    @DisplayName("Update an Owner that does not exist")
    public void updateMissingOwner() {
        //given
        final long id = new Random().nextLong();
        final Owner owner = new Owner().withId(id);
        given(repository.update(owner))
                .willReturn(Optional.empty());
        //then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() ->
                        resource.update(id, owner));
    }

    @Test
    @DisplayName("Remove an Owner that exists")
    public void removeExistingOwner() {
        //given
        final long id = new Random().nextLong();
        final Owner owner = new Owner().withId(id);
        given(repository.remove(id)).willReturn(Optional.of(owner));
        //when
        final Owner response = resource.remove(id);
        //then
        assertThat(response).isEqualTo(owner);
    }

    @Test
    @DisplayName("Remove an Owner that does not exist")
    public void removeMissingOwner() {
        //given
        final long id = new Random().nextLong();
        given(repository.remove(id)).willReturn(Optional.empty());
        //then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() ->
                        resource.remove(id));
    }

}
