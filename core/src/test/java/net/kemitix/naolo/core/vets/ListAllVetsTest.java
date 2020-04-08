package net.kemitix.naolo.core.vets;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.LongArbitrary;
import net.jqwik.api.arbitraries.SizableArbitrary;
import net.jqwik.api.arbitraries.StringArbitrary;
import net.kemitix.naolo.core.StreamZipper;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;
import org.assertj.core.api.WithAssertions;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static net.kemitix.naolo.core.vets.ListAllVets.request;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ListAllVetsTest implements WithAssertions {

    private final VeterinarianRepository repository = mock(VeterinarianRepository.class);

    private final ListAllVets useCase = ListAllVets.create(repository);

    @Provide
    public static Arbitrary<List<Veterinarian>> vets() {
        final LongArbitrary ids = Arbitraries.longs();
        final StringArbitrary names = Arbitraries.strings();
        final SizableArbitrary<Set<VetSpecialisation>> specialisations = Arbitraries.of(VetSpecialisation.class)
                .set().ofMinSize(0).ofMaxSize(VetSpecialisation.values().length);
        return Combinators.combine(ids, names, specialisations)
                .as((id, name, vetSpecs) ->
                        Veterinarian.builder()
                                .id(id)
                                .name(name)
                                .specialisations(vetSpecs)
                                .build())
                .list();
    }

    @Property
    public void listAll(
            @ForAll("vets") final List<Veterinarian> vets
    ) throws ExecutionException, InterruptedException {
        //given
        given(repository.findAll()).willReturn(vets.stream());
        //when
        final ListAllVets.Response response = useCase.invoke(request());
        //then
        final Stream<Tuple.Tuple2<Veterinarian, Veterinarian>> zipped =
                StreamZipper.zip(vets, response.getAllVeterinarians(), Tuple::of);
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