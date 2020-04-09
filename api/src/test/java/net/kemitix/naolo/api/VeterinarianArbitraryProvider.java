package net.kemitix.naolo.api;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class VeterinarianArbitraryProvider
        implements ArbitraryProvider {
    @Override
    public boolean canProvideFor(final TypeUsage typeUsage) {
        return typeUsage.isOfType(Veterinarian.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(
            final TypeUsage typeUsage,
            final SubtypeProvider subtypeProvider
    ) {
        final Arbitrary<Long> ids = Arbitraries.longs();
        final Arbitrary<String> names = Arbitraries.strings();
        final Arbitrary<List<VetSpecialisation>> specialities =
                Arbitraries.of(VetSpecialisation.class)
                        .list()
                        .ofMinSize(0)
                        .ofMaxSize(VetSpecialisation.values().length);
        return Collections.singleton(
                Combinators.combine(ids, names, specialities)
                        .as((id, name, vetSpecs) ->
                                new Veterinarian()
                                        .withId(id)
                                        .withName(name)
                                        .withSpecialisations(vetSpecs)));
    }
}
