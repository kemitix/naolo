package net.kemitix.naolo.entities;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;

import java.util.Collections;
import java.util.Set;

public class VetSpecialisationProvider
        implements ArbitraryProvider {
    @Override
    public boolean canProvideFor(final TypeUsage typeUsage) {
        return typeUsage.isOfType(VetSpecialisation.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(final TypeUsage typeUsage, final SubtypeProvider subtypeProvider) {
        return Collections.singleton(
                Arbitraries.of(VetSpecialisation.class));
    }
}
