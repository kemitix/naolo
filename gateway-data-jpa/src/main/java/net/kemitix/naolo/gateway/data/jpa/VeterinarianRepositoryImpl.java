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

package net.kemitix.naolo.gateway.data.jpa;

import net.kemitix.naolo.core.VeterinarianRepository;
import net.kemitix.naolo.entities.Veterinarian;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * DeltaSpike Data Gateway Repository for Veterinarians.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
@ApplicationScoped
class VeterinarianRepositoryImpl implements VeterinarianRepository {

    private final EntityManager entityManager;

    /**
     * Default constructor.
     */
    VeterinarianRepositoryImpl() {
        entityManager = null;
    }

    /**
     * CDI Constructor.
     *
     * @param entityManager the EntityManager
     */
    @Inject
    VeterinarianRepositoryImpl(final EntityManager entityManager) {
        this.entityManager = Objects.requireNonNull(entityManager, "EntityManager");
    }

    /**
     * Find all Veterinarians.
     *
     * @return a Stream of Veterinarians
     */
    @Override
    public Stream<Veterinarian> findAll() {
        return entityManager
                .createNamedQuery(VeterinarianJPA.FIND_ALL_VETS, Veterinarian.class)
                .getResultStream();
    }

}
