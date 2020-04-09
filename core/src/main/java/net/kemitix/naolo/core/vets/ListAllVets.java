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

import lombok.Builder;
import lombok.Getter;
import lombok.With;
import net.kemitix.naolo.core.UseCase;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Use-case to list all {@link Veterinarian}s.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
@Dependent
public class ListAllVets
        implements UseCase<ListAllVets.Request, ListAllVets.Response> {

    private static final Request REQUEST = new Request() {
    };

    private final VeterinarianRepository repository;

    /**
     * Constructor.
     *
     * @param repository the Veterinarian Repository
     */
    public ListAllVets(final VeterinarianRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new {@link ListAllVets} use-case.
     *
     * @param veterinarianRepository the Veterinarian repository
     * @return a new VeterinariansListAll use-case
     */
    public static ListAllVets create(final VeterinarianRepository veterinarianRepository) {
        return new ListAllVets(veterinarianRepository);
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
    public Response invoke(final Request request) {
        return Response.builder()
                .veterinarians(
                        repository.findAll()
                                .collect(Collectors.toList()))
                .build();
    }

    @Getter
    @With
    @Builder
    public static class Response {
        List<Veterinarian> veterinarians;
    }

    public static class Request {
    }

}
