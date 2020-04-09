package net.kemitix.naolo.api;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.kemitix.naolo.core.vets.AddVet;
import net.kemitix.naolo.core.vets.GetVet;
import net.kemitix.naolo.core.vets.ListAllVets;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
public class VetResourceTest implements WithAssertions {

    private final VeterinarianRepository repository =
            mock(VeterinarianRepository.class);
    private final VetResource resource =
            new VetResource(
                    new ListAllVets(repository),
                    new AddVet(repository),
                    new GetVet(repository));
    private final Long id = new Random().nextLong();
    private final Veterinarian vet =
            new Veterinarian(
                    id,
                    "name",
                    Collections.singletonList(
                            VetSpecialisation.RADIOLOGY)
    );

    @Property
    @SuppressWarnings("unchecked")
    public void canGetAllVets(
            @ForAll final List<Veterinarian> vets
    ) throws ExecutionException, InterruptedException {
        //given
        given(repository.findAll()).willReturn(vets.stream());
        //when
        final Response response = resource.allVets();
        //then
        final List<Veterinarian> entity =
                (List<Veterinarian>) response.getEntity();
        assertThat(entity).hasSize(vets.size());
    }

    @Property
    public void canAddVet(
            @ForAll final Veterinarian vet
    ) {
        //given
        given(repository.add(any(Veterinarian.class)))
                .willAnswer(call ->
                        ((Veterinarian) call.getArgument(0))
                                .withId(id));
        //when
        final Response response = resource.add(vet);
        //then
        assertThat(response.hasEntity()).isTrue();
        assertThat(((Veterinarian) response.getEntity()))
                .extracting(Veterinarian::getId)
                .isNotNull();
    }

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
