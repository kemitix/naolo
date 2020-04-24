package net.kemitix.naolo.vets;

import net.kemitix.naolo.core.jpa.EntityRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
@ExtendWith(MockitoExtension.class)
public class VetResourceTest implements WithAssertions {

    private final EntityRepository<Veterinarian> repository;
    private final Long id = new Random().nextLong();
    private final VetResource resource;

    public VetResourceTest(
            @Mock final EntityRepository<Veterinarian> repository
    ) {
        this.repository = repository;
        resource =
                new VetResource(
                        new ListVets(repository),
                        new AddVet(repository),
                        new GetVet(repository),
                        new UpdateVet(repository),
                        new RemoveVet(repository));
    }

    @Test
    @DisplayName("Has a no-args constructor")
    public void hasNoArgsConstructor() {
        assertThatCode(VetResource::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Get All Vets")
    public void canGetAllVets() {
        //given
        final List<Veterinarian> vets = Arrays.asList(
                new Veterinarian().withName("bob"),
                new Veterinarian().withName("sam"));
        given(repository.findAll()).willReturn(vets.stream());
        //when
        final List<Veterinarian> response = resource.all();
        //then
        assertThat(response).isEqualTo(vets);
    }

    @Test
    @DisplayName("Add a Vet")
    public void canAddVet() {
        //given
        final Veterinarian vet = new Veterinarian().withId(id);
        given(repository.add(any(Veterinarian.class)))
                .willAnswer(call ->
                        ((Veterinarian) call.getArgument(0))
                                .withId(id));
        //when
        final Response response = resource.add(vet);
        //then
        final URI location = response.getLocation();
        assertThat(location).isEqualTo(URI.create("/vets/" + id));
    }

    @Nested
    @DisplayName("Get a Vet")
    public class GetVetTests {
        private final Veterinarian vet =
                new Veterinarian(
                        id,
                        "name",
                        Collections.singletonList(
                                VetSpecialisation.RADIOLOGY)
                );
        @Test
        @DisplayName("Get a Vet that exists")
        public void getExistingVet() {
            //given
            given(repository.find(id)).willReturn(Optional.of(vet));
            //when
            final Veterinarian response = resource.get(id);
            //then
            assertThat(response)
                    .as("Found the Veterinarian")
                    .isEqualTo(vet);
        }

        @Test
        @DisplayName("Get a Vet that does not exist")
        public void getMissingVet() {
            //given
            given(repository.find(id)).willReturn(Optional.empty());
            //then
            assertThatExceptionOfType(NotFoundException.class)
                    .isThrownBy(() -> resource.get(id));
        }
    }
    @Nested
    @DisplayName("Update a Vet")
    public class UpdateVetTests {
        private final Veterinarian expectedVet =
                new Veterinarian()
                        .withId(id)
                        .withName("update name")
                        .withSpecialisations(Arrays.asList(
                                VetSpecialisation.DENTISTRY,
                                VetSpecialisation.SURGERY));
        @Test
        @DisplayName("That exists")
        public void UpdateVetThatExists() {
            //given
            given(repository.update(expectedVet))
                    .willReturn(Optional.of(expectedVet));
            //when
            final Veterinarian response =
                    resource.update(id, expectedVet);
            //then
            assertThat(response)
                    .as("Returned the Veterinarian")
                    .isEqualTo(expectedVet);
        }

        @Test
        @DisplayName("That does not exist")
        public void UpdateMissingVet() {
            //given
            given(repository.update(expectedVet))
                    .willReturn(Optional.empty());
            //then
            assertThatExceptionOfType(NotFoundException.class)
                    .isThrownBy(() ->
                            resource.update(id, expectedVet));
        }
    }
    @Nested
    @DisplayName("Remove a Vet")
    public class RemoveVetTests {
        @Test
        @DisplayName("That exists")
        public void removeExistingVet() {
            //given
            final Veterinarian vet = new Veterinarian();
            given(repository.remove(id))
                    .willReturn(Optional.of(vet));
            //when
            final Veterinarian response =
                    resource.remove(id);
            //then
            assertThat(response)
                    .isEqualTo(vet);
        }

        @Test
        @DisplayName("That does not exist")
        public void removeMissingVet() {
            //given
            given(repository.find(id)).willReturn(Optional.empty());
            //then
            assertThatExceptionOfType(NotFoundException.class)
                    .isThrownBy(() ->
                            resource.remove(id));
        }
    }
}