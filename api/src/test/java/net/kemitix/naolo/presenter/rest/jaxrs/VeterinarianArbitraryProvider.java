package net.kemitix.naolo.presenter.rest.jaxrs;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;

import java.util.Collections;
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
        final Arbitrary<Set<VetSpecialisation>> specialities =
                Arbitraries.of(VetSpecialisation.class)
                        .set()
                        .ofMinSize(0)
                        .ofMaxSize(VetSpecialisation.values().length);
        return Collections.singleton(
                Combinators.combine(ids, names, specialities)
                        .as((id, name, vetSpecs) ->
                                Veterinarian.builder()
                                        .id(id)
                                        .name(name)
                                        .specialisations(vetSpecs)
                                        .build()));
    }
}