package net.kemitix.naolo.storage.plugins.h2;

import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianJPA;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.function.Function;
import java.util.stream.Stream;

@ApplicationScoped
public class H2VeterinarianRepository
        implements VeterinarianRepository {

    private final EntityManager entityManager;

    public H2VeterinarianRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Stream<Veterinarian> findAll() {
        return entityManager
                .createQuery(
                        "Select v From VeterinarianJPA v Order By v.name",
                        VeterinarianJPA.class)
                .getResultStream()
                .map(jpaToEntity());
    }

    private Function<VeterinarianJPA, Veterinarian> jpaToEntity() {
        return jpa -> Veterinarian.builder()
                .id(jpa.getId())
                .name(jpa.getName())
                .specialisations(
                        VetSpecialisation.parse(
                                jpa.getSpecialisations()))
                .build();
    }
}
