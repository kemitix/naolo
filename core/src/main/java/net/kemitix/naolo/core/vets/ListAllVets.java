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

package net.kemitix.naolo.core.vets;

import net.kemitix.naolo.core.UseCase;
import net.kemitix.naolo.entities.Veterinarian;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Use-case to list all {@link Veterinarian}s.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
public class ListAllVets
        implements UseCase<ListAllVets.Request, ListAllVets.Response> {

    private static final Request REQUEST = new Request() {
    };

    private final VetsRepository repository;

    /**
     * Constructor.
     *
     * @param repository the Veterinarian Repository
     */
    public ListAllVets(final VetsRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new {@link ListAllVets} use-case.
     *
     * @param vetsRepository the Veterinarian repository
     * @return a new VeterinariansListAll use-case
     */
    public static ListAllVets create(final VetsRepository vetsRepository) {
        return new ListAllVets(vetsRepository);
    }

    /**
     * Returns the empty request.
     *
     * @return the empty request object
     */
    public static Request request() {
        return REQUEST;
    }

    /**
     * Invoke the UseCase.
     *
     * <p>This implementation requires the parameter to be {@link #request()}.</p>
     *
     * @param request the result of {@link #request()}
     * @return the Response
     */
    @Override
    public CompletableFuture<Response> invoke(final Request request) {
        return CompletableFuture.supplyAsync(() -> () -> {
            return repository.findAll().collect(Collectors.toList());
        });
    }

    /**
     * Empty Request Parameter.
     *
     * <p>Use the {@link #request()} to obtain an instance.</p>
     */
    interface Request {

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
