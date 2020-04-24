package net.kemitix.naolo.vets;

import lombok.Getter;
import net.kemitix.naolo.storage.EntityRepository;
import net.kemitix.naolo.storage.UpdateEntityRequest;
import net.kemitix.naolo.storage.UpdateEntityUseCase;

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
