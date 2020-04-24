package net.kemitix.naolo.vets;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.EntityRepository;
import net.kemitix.naolo.core.jpa.RemoveEntityRequest;
import net.kemitix.naolo.core.jpa.RemoveEntityUseCase;

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
