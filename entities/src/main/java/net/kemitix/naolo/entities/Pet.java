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

import java.time.LocalDateTime;

/**
 * The Pet.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
public final class Pet {

    private final long id;
    private final String name;
    private final LocalDateTime dateOfBirth;
    private final PetType type;
    private final long ownerId;

    /**
     * Constructor.
     *
     * @param id the Pet ID
     * @param name the Pets name
     * @param dateOfBirth the Pets Date of Birth
     * @param type the type of Pet
     * @param ownerId the Owners ID
     */
    public Pet(final long id,
               final String name,
               final LocalDateTime dateOfBirth,
               final PetType type,
               final long ownerId) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
        this.ownerId = ownerId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public PetType getType() {
        return type;
    }

    public long getOwnerId() {
        return ownerId;
    }
}
