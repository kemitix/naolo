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

import java.util.HashSet;
import java.util.Set;

/**
 * A Veterinarian.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
public final class Veterinarian {

    private final long id;
    private final String name;
    private final Set<VetSpecialisation> specialisations;

    private Veterinarian(final long id,
                        final String name,
                        final Set<VetSpecialisation> specialisations) {
        this.id = id;
        this.name = name;
        this.specialisations = new HashSet<>(specialisations);
    }

    /**
     * Create a new Veterinarian object.
     *
     * @param id the Veterinarian ID
     * @param name the Veterinarians Name
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
