package net.kemitix.naolo.core.vets;

import net.kemitix.naolo.core.StreamZipper;
import net.kemitix.naolo.core.Tuple;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static net.kemitix.naolo.core.vets.ListAllVets.request;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ListAllVetsTest implements WithAssertions {

    private final VeterinarianRepository repository = mock(VeterinarianRepository.class);

    private final ListAllVets useCase = new ListAllVets(repository);

    @Test
    @DisplayName("List all Vets")
    public void listAll() {
        //given
        final List<Veterinarian> vets = Arrays.asList(
                new Veterinarian().withId(42L).withName("Name 1")
                        .withSpecialisations(Collections.singletonList(
                                VetSpecialisation.RADIOLOGY
                        )),
                new Veterinarian().withId(22L).withName("Name 2")
                        .withSpecialisations(Arrays.asList(
                                VetSpecialisation.DENTISTRY,
                                VetSpecialisation.SURGERY
                        ))
                );
        given(repository.findAll()).willReturn(vets.stream());
        //when
        final ListAllVets.Response response = useCase.invoke(request());
        //then
        final Stream<Tuple<Veterinarian, Veterinarian>> zipped =
                StreamZipper.zip(vets, response.getVeterinarians(), Tuple::of);
        assertThat(zipped).hasSize(vets.size())
                .allSatisfy(t -> {
                    final Veterinarian s = t.get1();
                    final Veterinarian r = t.get2();
                    assertThat(r.getId()).isEqualTo(s.getId());
                    assertThat(r.getName()).isEqualTo(s.getName());
                    assertThat(r.getSpecialisations()).isEqualTo(s.getSpecialisations());
                });
    }
}