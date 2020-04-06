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

package net.kemitix.naolo.gateway.data.common;

import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Provides the EntityManager.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
@Log
@ApplicationScoped
public class EntityManagerProducer {

    private final String unitName;

    /**
     * Default Constructor.
     */
    public EntityManagerProducer() {
        unitName = null;
    }

    /**
     * CDI Constructor.
     *
     * @param unitName the name of the Persistence Unit
     */
    @Inject
    public EntityManagerProducer(@GatewayPersistenceUnitName final String unitName) {
        this.unitName = unitName;
    }

    /**
     * Producer for EntityManagerFactory.
     *
     * @return an EntityManagerFactory
     */
    @Produces
    @ApplicationScoped
    public EntityManagerFactory entityManagerFactory() {
        log.info("Create EntityManagerFactory for " + unitName);
        return Persistence.createEntityManagerFactory(unitName);
    }

    /**
     * Producer for EntityManager.
     *
     * @param entityManagerFactory an EntityManagerFactory
     * @return an EntityManager
     */
    @Produces
    public EntityManager entityManager(final EntityManagerFactory entityManagerFactory) {
        log.info("Create EntityManager for " + unitName);
        return entityManagerFactory.createEntityManager();
    }

}
