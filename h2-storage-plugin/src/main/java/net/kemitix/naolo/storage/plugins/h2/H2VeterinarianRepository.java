package net.kemitix.naolo.storage.plugins.h2;

import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianEntityToJPA;
import net.kemitix.naolo.storage.spi.VeterinarianJPA;
import net.kemitix.naolo.storage.spi.VeterinarianJPAToEntity;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.stream.Stream;

@ApplicationScoped
public class H2VeterinarianRepository
        implements VeterinarianRepository {

    private final EntityManager entityManager;
    private final VeterinarianEntityToJPA entityToJpa;
    private final VeterinarianJPAToEntity jpaToEntity;

    public H2VeterinarianRepository(
            final EntityManager entityManager,
            final VeterinarianEntityToJPA entityToJpa,
            final VeterinarianJPAToEntity jpaToEntity
    ) {
        this.entityManager = entityManager;
        this.entityToJpa = entityToJpa;
        this.jpaToEntity = jpaToEntity;
    }

    @Override
    public Stream<Veterinarian> findAll() {
        return entityManager
                .createQuery(
                        "Select v " +
                                "From VeterinarianJPA v " +
                                "Order By v.name",
                        VeterinarianJPA.class)
                .getResultStream()
                .map(jpaToEntity);
    }

    @Transactional
    @Override
    public Veterinarian add(final Veterinarian veterinarian) {
        return entityToJpa
                .andThen(entityManager::merge)
                .andThen(VeterinarianJPA::getId)
                .andThen(veterinarian::withId)
                .apply(veterinarian);
    }

}
