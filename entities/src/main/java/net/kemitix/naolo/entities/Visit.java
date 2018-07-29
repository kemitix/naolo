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
 * A Visit by a Pet with a Veterinarian.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
public final class Visit {

    private final long id;
    private final long petId;
    private final long vetId;
    private final LocalDateTime dateTime;
    private final String description;

    /**
     * Constructor.
     *
     * @param id the Visit ID
     * @param petId the Pet ID
     * @param vetId the Veterinarian ID
     * @param dateTime the Date and Time of the Visit
     * @param description the Description
     */
    public Visit(final long id,
                 final long petId,
                 final long vetId,
                 final LocalDateTime dateTime,
                 final String description) {
        this.id = id;
        this.petId = petId;
        this.vetId = vetId;
        this.dateTime = dateTime;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public long getPetId() {
        return petId;
    }

    public long getVetId() {
        return vetId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }
}
