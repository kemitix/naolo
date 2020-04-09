package net.kemitix.naolo.storage.plugins;

import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class VeterinarianRepositoryImplTest
        implements WithAssertions {

    private final Veterinarian unmanagedVet = new Veterinarian();
    private final Veterinarian managedVet = new Veterinarian();
    @Mock
    private EntityManager entityManager;
    private VeterinarianRepository repository;
    @Mock
    private TypedQuery<Veterinarian> query;
    private final long id = new Random().nextLong();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        repository = new VeterinarianRepositoryImpl(entityManager);
    }

    @Test
    @DisplayName("List All invokes named query")
    public void listAll() {
        //given
        given(entityManager
                .createNamedQuery(Veterinarian.FIND_ALL, Veterinarian.class))
                .willReturn(query);
        //when
        final Stream<Veterinarian> result = repository.findAll();
        //then
        verify(query).getResultStream();
    }

    @Test
    @DisplayName("Adding a Vet")
    public void add() {
        //given
        given(entityManager.merge(unmanagedVet)).willReturn(managedVet);
        //when
        final Veterinarian result = repository.add(unmanagedVet);
        //then
        verify(entityManager).persist(managedVet);
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
}