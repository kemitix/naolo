package net.kemitix.naolo.storage.plugins;

import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.EntityRepository;
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
public class VeterinarianRepositoryTest
        implements WithAssertions {

    private final Veterinarian unmanagedVet = new Veterinarian();
    private final Veterinarian managedVet = new Veterinarian();
    private final long id = new Random().nextLong();
    private final EntityManager entityManager;
    private final EntityRepository<Veterinarian> repository;
    private final TypedQuery<Veterinarian> allVetsQuery;
    private final Stream<Veterinarian> allVetsStream;

    public VeterinarianRepositoryTest(
            @Mock final EntityManager entityManager,
            @Mock final TypedQuery<Veterinarian> allVetsQuery,
            @Mock final Stream<Veterinarian> allVetsStream) {
        this.entityManager = entityManager;
        repository = new VeterinarianRepository(entityManager);
        this.allVetsQuery = allVetsQuery;
        this.allVetsStream = allVetsStream;
    }

    @Test
    @DisplayName("List All invokes named query")
    public void listAll() {
        //given
        given(entityManager
                .createNamedQuery(Veterinarian.FIND_ALL, Veterinarian.class))
                .willReturn(allVetsQuery);
        given(allVetsQuery.getResultStream()).willReturn(allVetsStream);
        //when
        final Stream<Veterinarian> result = repository.findAll();
        //then
        assertThat(result).isSameAs(allVetsStream);
    }

    @Test
    @DisplayName("Adding a Vet")
    public void add() {
        //given
        given(entityManager.merge(unmanagedVet)).willReturn(managedVet);
        //when
        final Veterinarian result = repository.add(unmanagedVet);
        //then
        assertThat(result).isSameAs(managedVet);
    }

    @Test
    @DisplayName("Getting a Vet")
    public void getVet() {
        //given
        given(entityManager.find(Veterinarian.class, id))
                .willReturn(managedVet);
        //when
        final Optional<Veterinarian> result = repository.find(id);
        //then
        assertThat(result).contains(managedVet);
    }

    @Test
    @DisplayName("Update a Vet")
    public void updateVet() {
        //given
        final Veterinarian vet = new Veterinarian().withId(id);
        given(entityManager.find(Veterinarian.class, id))
                .willReturn(vet);
        given(entityManager.merge(vet))
                .willReturn(vet);
        //when
        final Optional<Veterinarian> result = repository.update(vet);
        //then
        assertThat(result).contains(vet);
    }

    @Test
    @DisplayName("Remove a Vet")
    public void removeVet() {
        //given
        given(entityManager.find(Veterinarian.class, id))
                .willReturn(managedVet);
        //when
        final Optional<Veterinarian> result = repository.remove(id);
        //then
        verify(entityManager).remove(managedVet);
        assertThat(result).contains(managedVet);
    }
}
