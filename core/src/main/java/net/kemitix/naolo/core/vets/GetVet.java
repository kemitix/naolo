package net.kemitix.naolo.core.vets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.GetEntityRequest;
import net.kemitix.naolo.core.GetEntityUseCase;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class GetVet
        implements GetEntityUseCase<Veterinarian> {

    @Getter
    private final EntityRepository<Veterinarian> repository;

    @Override
    public GetEntityRequest<Veterinarian> request(final long id) {
        return GetEntityRequest.create(id);
    }
}
