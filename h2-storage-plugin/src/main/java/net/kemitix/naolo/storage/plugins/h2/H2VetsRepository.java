package net.kemitix.naolo.storage.plugins.h2;

import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.Vet;
import net.kemitix.naolo.storage.spi.VetsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.stream.Stream;

@ApplicationScoped
public class H2VetsRepository implements VetsRepository {

    @Inject
    EntityManager entityManager;

    @Override
    public Stream<Veterinarian> findAll() {
        return entityManager
                .createQuery(
                        "Select v From Vet v Order By v.name",
                        Vet.class)
                .getResultStream()
                .map(jpa -> new Veterinarian(
                            jpa.getId(),
                            jpa.getName(),
                            jpa.getSpecialisations()));
    }
}
