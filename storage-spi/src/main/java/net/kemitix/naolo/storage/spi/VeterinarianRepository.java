/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Paul Campbell
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

package net.kemitix.naolo.storage.spi;

import net.kemitix.naolo.entities.Veterinarian;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Repository for {@link Veterinarian}.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
public interface VeterinarianRepository {

    /**
     * Find all Veterinarians.
     *
     * @return a Stream of Veterinarians
     */
    Stream<Veterinarian> findAll();

    /**
     * Adds a new Veterinarian.
     *
     * @param veterinarian the vet to add
     * @return the added vet
     */
    Veterinarian add(Veterinarian veterinarian);

    /**
     * Finds a Veterinarian for the id.
     *
     * @param id the id of the vet to find
     * @return an Optional containing the vet if found, empty otherwise.
     */
    Optional<Veterinarian> find(long id);

    Optional<Veterinarian> update(Veterinarian veterinarian);
}
