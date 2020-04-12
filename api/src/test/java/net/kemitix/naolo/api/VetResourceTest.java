package net.kemitix.naolo.api;

import net.kemitix.naolo.core.vets.*;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;
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

    private final VeterinarianRepository repository;
    private final Long id = new Random().nextLong();
    private final VetResource resource;

    public VetResourceTest(
            @Mock final VeterinarianRepository repository
    ) {
        this.repository = repository;
        resource =
                new VetResource(
                        new ListAllVets(repository),
                        new AddVet(repository),
                        new GetVet(repository),
                        new UpdateVet(repository),
                        new RemoveVet(repository));
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
        final Response response = resource.all();
        //then
        assertThat(response.getEntity()).isEqualTo(vets);
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
            final Response response = resource.get(id);
            //then
            assertThat(response.getStatus())
                    .as("Status Code 200")
                    .isEqualTo(200);
            assertThat(response.hasEntity())
                    .as("Vet found")
                    .isTrue();
            final Veterinarian entity =
                    (Veterinarian) response.getEntity();
            assertThat(entity)
                    .as("Found the Veterinarian")
                    .isEqualTo(vet);
        }

        @Test
        @DisplayName("Get a Vet that does not exist")
        public void getMissingVet() {
            //given
            given(repository.find(id)).willReturn(Optional.empty());
            //when
            final Response response = resource.get(id);
            //then
            assertThat(response.getStatus())
                    .as("Status Code 404")
                    .isEqualTo(404);
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
            final Response response =
                    resource.update(id, expectedVet);
            //then
            assertThat(response.getStatus())
                    .as("Status Code 200")
                    .isEqualTo(200);
            assertThat(response.hasEntity())
                    .as("Vet found")
                    .isTrue();
            final Veterinarian entity =
                    (Veterinarian) response.getEntity();
            assertThat(entity)
                    .as("Returned the Veterinarian")
                    .isEqualTo(expectedVet);

        }

        @Test
        @DisplayName("That does not exist")
        public void UpdateMissingVet() {
            //given
            given(repository.update(expectedVet))
                    .willReturn(Optional.empty());
            //when
            final Response response =
                    resource.update(id, expectedVet);
            //then
            assertThat(response.getStatus())
                    .as("Status Code 404")
                    .isEqualTo(404);
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
            final Response response =
                    resource.remove(id);
            //then
            assertThat(response.getStatus())
                    .as("Status Code 200")
                    .isEqualTo(HttpServletResponse.SC_OK);
        }

        @Test
        @DisplayName("That does not exist")
        public void removeMissingVet() {
            //given
            given(repository.find(id)).willReturn(Optional.empty());
            //when
            final Response response =
                    resource.remove(id);
            //then
            assertThat(response.getStatus())
                    .as("Status Code 404")
                    .isEqualTo(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}