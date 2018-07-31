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

package net.kemitix.naolo.gateway.data.spring;

import net.kemitix.naolo.core.VeterinarianRepository;
import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Spring Gateway Repository for Veterinarians.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
@Repository
class VeterinarianRepositoryImpl implements VeterinarianRepository {

    private final VeterinarianRepositorySpring repository;
    private final Converter<VeterinarianJPA, Veterinarian> fromJPA;

    /**
     * Constructor.
     *
     * @param repository the Spring Data repository
     * @param fromJPA the converter from JPA to core entity
     */
    VeterinarianRepositoryImpl(
            final VeterinarianRepositorySpring repository,
            final Converter<VeterinarianJPA, Veterinarian> fromJPA
    ) {
        this.repository = repository;
        this.fromJPA = fromJPA;
    }

    @Override
    public Stream<Veterinarian> findAll() {
        return repository.findAll().stream()
                .map(fromJPA::convert);
    }

    /**
     * Converts VeterinarianJPA to core entity type.
     */
    @Component
    static class FromJPA implements Converter<VeterinarianJPA, Veterinarian> {

        @Override
        public Veterinarian convert(final VeterinarianJPA source) {
            return Veterinarian.create(
                    source.getId(),
                    source.getName(),
                    source.getSpecialisations().stream()
                            .map(VetSpecialisation::valueOf)
                            .collect(Collectors.toSet()));
        }
    }

    /**
     * Converts core entity type to VeterinarianJPA.
     */
    @Component
    static class ToJPA implements Converter<Veterinarian, VeterinarianJPA> {
        @Override
        public VeterinarianJPA convert(final Veterinarian source) {
            return new VeterinarianJPA(
                    source.getId(),
                    source.getName(),
                    source.getSpecialisations().stream()
                            .map(Enum::toString)
                            .collect(Collectors.toSet()));
        }
    }
}
