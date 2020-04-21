package net.kemitix.naolo.core.vets;

import lombok.Getter;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.EntityRepository;
import net.kemitix.naolo.storage.spi.UpdateEntityRequest;
import net.kemitix.naolo.storage.spi.UpdateEntityUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UpdateVet
        implements UpdateEntityUseCase<Veterinarian> {

    @Getter
    private final EntityRepository<Veterinarian> repository;

    @Inject
    public UpdateVet(final EntityRepository<Veterinarian> repository) {
        this.repository = repository;
    }

    @Override
    public UpdateEntityRequest<Veterinarian> request(
            final Veterinarian veterinarian
    ) {
        return UpdateEntityRequest.create(veterinarian);
    }
}
