/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Paul Campbell
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.kemitix.naolo.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A Veterinarian.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
public final class Veterinarian {

    private final Long id;
    private final String name;
    private final Set<VetSpecialisation> specialisations;

    /**
     * Default Constructor.
     */
    public Veterinarian() {
        id = null;
        name = null;
        specialisations = new HashSet<>();
    }

    /**
     * Constructor.
     *
     * @param id              the Veterinarian ID
     * @param name            the Veterinarians Name
     * @param specialisations the Specialisations of the Veterinarian
     */
    public Veterinarian(final long id,
                        final String name,
                        final Set<VetSpecialisation> specialisations) {
        this.id = id;
        this.name = name;
        this.specialisations = new HashSet<>(specialisations);
    }

    /**
     * Constructor parsing ';' delimited VetSpecialisation values.
     *
     * @param id              the Veterinarian ID
     * @param name            the Veterinarians Name
     * @param specialisations the Specialisations of the Veterinarian as a ';' delimited String
     */
    public Veterinarian(final long id,
                        final String name,
                        final String specialisations) {
        this.id = id;
        this.name = name;
        this.specialisations = Arrays.stream(specialisations.split(";"))
                .filter(s -> s.length() > 0)
                .map(VetSpecialisation::valueOf).collect(Collectors.toSet());
    }

    /**
     * Create a new Veterinarian object.
     *
     * @param id              the Veterinarian ID
     * @param name            the Veterinarians Name
     * @param specialisations the Specialisations of the Veterinarian
     * @return a new Veterinarian
     */
    public static Veterinarian create(
            final long id,
            final String name,
            final Set<VetSpecialisation> specialisations
    ) {
        return new Veterinarian(id, name, specialisations);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<VetSpecialisation> getSpecialisations() {
        return new HashSet<>(specialisations);
    }

}
