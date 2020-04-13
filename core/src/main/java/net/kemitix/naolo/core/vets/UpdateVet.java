package net.kemitix.naolo.core.vets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.UpdateEntityRequest;
import net.kemitix.naolo.core.UpdateEntityUseCase;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class UpdateVet
        implements UpdateEntityUseCase<Veterinarian> {

    @Getter
    private final EntityRepository<Veterinarian> repository;

    @Override
    public UpdateEntityRequest<Veterinarian> request(
            final Veterinarian veterinarian
    ) {
        return UpdateEntityRequest.create(veterinarian);
    }
}
