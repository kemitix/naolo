package net.kemitix.naolo.entities;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.assertj.core.api.WithAssertions;

import java.util.Set;
import java.util.stream.Collectors;

public class VetSpecialisationTest
        implements WithAssertions {

    @Property
    public void parseString(
            @ForAll final Set<VetSpecialisation> specialisations
    )  {
        //given
        final String collect = specialisations.stream()
                .map(VetSpecialisation::name)
                .collect(Collectors.joining(";"));
        //when
        final Set<VetSpecialisation> result =
                VetSpecialisation.parse(collect);
        //then
        assertThat(result).isEqualTo(specialisations);
    }
}