package net.kemitix.naolo.vets;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.EntityRepository;
import net.kemitix.naolo.core.jpa.GetEntityRequest;
import net.kemitix.naolo.core.jpa.GetEntityUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GetVet
        implements GetEntityUseCase<Veterinarian> {

    @Getter
    private final EntityRepository<Veterinarian> repository;

    @Inject
    public GetVet(final EntityRepository<Veterinarian> repository) {
        this.repository = repository;
    }

    @Override
    public GetEntityRequest<Veterinarian> request(final long id) {
        return GetEntityRequest.create(id);
    }
}
