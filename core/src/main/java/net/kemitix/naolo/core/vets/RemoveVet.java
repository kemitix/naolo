package net.kemitix.naolo.core.vets;

import lombok.Getter;
import net.kemitix.naolo.core.RemoveEntityRequest;
import net.kemitix.naolo.core.RemoveEntityUseCase;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RemoveVet
        implements RemoveEntityUseCase<Veterinarian> {

    @Getter
    private final EntityRepository<Veterinarian> repository;

    @Inject
    public RemoveVet(final EntityRepository<Veterinarian> repository) {
        this.repository = repository;
    }

    @Override
    public RemoveEntityRequest<Veterinarian> request(final long id) {
        return RemoveEntityRequest.create(id);
    }

}
