package net.kemitix.naolo.war;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class AppResources {

    @Produces
    @PersistenceContext(unitName = "naolo")
    private EntityManager em;

}
