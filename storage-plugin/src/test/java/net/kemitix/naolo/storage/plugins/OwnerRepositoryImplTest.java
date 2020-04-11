package net.kemitix.naolo.storage.plugins;

import net.kemitix.naolo.entities.Owner;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OwnerRepositoryImplTest
        implements WithAssertions {

    private final EntityManager entityManager;
    private final OwnerRepositoryImpl repository;
    private final TypedQuery<Owner> allOwnersQuery;
    private final Stream<Owner> allOwnersStream;
    private final long id = new Random().nextLong();
    private final Owner managedOwner = new Owner();
    private final Owner unmanagedOwner = new Owner();

    public OwnerRepositoryImplTest(
            @Mock final EntityManager entityManager,
            @Mock final TypedQuery<Owner> allOwnersQuery,
            @Mock final Stream<Owner> allOwnersStream
    ) {
        this.entityManager = entityManager;
        repository = new OwnerRepositoryImpl(entityManager);
        this.allOwnersQuery = allOwnersQuery;
        this.allOwnersStream = allOwnersStream;
    }

    @Test
    @DisplayName("List All invokes named query")
    public void listAll() {
        //given
        given(entityManager
                .createNamedQuery(Owner.FIND_ALL, Owner.class))
                .willReturn(allOwnersQuery);
        given(allOwnersQuery.getResultStream())
                .willReturn(allOwnersStream);
        //when
        final Stream<Owner> result = repository.findAll();
        //then
        assertThat(result).isSameAs(allOwnersStream);
    }

    @Test
    @DisplayName("Adding an Owner")
    public void addOwner() {
        //given
        given(entityManager.merge(unmanagedOwner)).willReturn(managedOwner);
        //when
        final Owner result = repository.add(unmanagedOwner);
        //then
        assertThat(result).isSameAs(managedOwner);
    }

    @Test
    @DisplayName("Getting an Owner")
    public void getOwner() {
        //given
        given(entityManager.find(Owner.class, id))
                .willReturn(managedOwner);
        //when
        final Optional<Owner> result = repository.find(id);
        //then
        assertThat(result).contains(managedOwner);
    }

    @Test
    @DisplayName("Update an Owner")
    public void updateOwner() {
        //given
        final Owner owner = unmanagedOwner.withId(id);
        given(entityManager.find(Owner.class, id))
                .willReturn(managedOwner);
        given(entityManager.merge(owner)).willReturn(managedOwner);
        //when
        final Optional<Owner> result = repository.update(owner);
        //then
        assertThat(result).contains(managedOwner);
    }

    @Test
    @DisplayName("Remove an Owner")
    public void removeOwner() {
        //given
        final Owner owner = managedOwner.withId(id);
        given(entityManager.find(Owner.class, id))
                .willReturn(owner);
        //when
        final Optional<Owner> result = repository.remove(id);
        //then
        verify(entityManager).remove(owner);
        assertThat(result).contains(owner);
    }
}