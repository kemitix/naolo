package net.kemitix.naolo.core.owners;

import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.OwnerRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Random;

import static org.mockito.BDDMockito.given;

public class GetOwnerTest
        implements WithAssertions {

    private final long id = new Random().nextLong();
    private final Owner owner = new Owner();
    @Mock
    private OwnerRepository repository;
    private GetOwner getOwner;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getOwner = new GetOwner(repository);
    }

    @Test
    @DisplayName("Get a Vet that exists")
    public void getExistingVet() {
        //given
        given(repository.find(id))
                .willReturn(Optional.of(owner));
        final GetOwner.Request request =
                GetOwner.Request.builder()
                        .id(id)
                        .build();
        //when
        final GetOwner.Response response = getOwner.invoke(request);
        //then
        assertThat(response.getOwner())
                .contains(owner);
    }

    @Test
    @DisplayName("Get an Owner that does not exist")
    public void getMissingOwner() {
        //given
        given(repository.find(id))
                .willReturn(Optional.empty());
        final GetOwner.Request request =
                GetOwner.Request.builder()
                        .id(id)
                        .build();
        //when
        final GetOwner.Response response = getOwner.invoke(request);
        //then
        assertThat(response.getOwner())
                .isEmpty();
    }
}