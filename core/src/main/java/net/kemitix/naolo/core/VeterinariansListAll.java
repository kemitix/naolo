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

package net.kemitix.naolo.core;

import net.kemitix.naolo.entities.Veterinarian;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Use-case to list all {@link Veterinarian}s.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
public final class VeterinariansListAll
        implements UseCase<VeterinariansListAll.Request, VeterinariansListAll.Response> {

    private final VeterinarianRepository repository;

    private VeterinariansListAll(final VeterinarianRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new {@link VeterinariansListAll} use-case.
     *
     * @param veterinarianRepository the Veterinarian repository
     * @return a new VeterinariansListAll use-case
     */
    public static VeterinariansListAll create(final VeterinarianRepository veterinarianRepository) {
        return new VeterinariansListAll(veterinarianRepository);
    }

    @Override
    public Response invoke(final Request request) {
        return () -> repository.findAll().collect(Collectors.toList());
    }

    /**
     * Request Parameter.
     */
    public static class Request {
        //no parameters
    }

    /**
     * Response.
     */
    public interface Response {

        /**
         * The list of all Veterinarians.
         *
         * @return the list of all Veterinarians
         */
        List<Veterinarian> getAllVeterinarians();

    }

}
