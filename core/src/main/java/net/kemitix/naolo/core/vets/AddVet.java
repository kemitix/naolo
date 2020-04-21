package net.kemitix.naolo.core.vets;

import lombok.Getter;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.AddEntityRequest;
import net.kemitix.naolo.storage.spi.AddEntityUseCase;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddVet
        implements AddEntityUseCase<Veterinarian> {

    @Getter
    private final EntityRepository<Veterinarian> repository;

    @Inject
    public AddVet(final EntityRepository<Veterinarian> repository) {
        this.repository = repository;
    }

    @Override
    public AddEntityRequest<Veterinarian> request(
            final Veterinarian veterinarian
    ) {
        return AddEntityRequest.create(veterinarian);
    }
}
