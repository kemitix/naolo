package net.kemitix.naolo.vets;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.AddEntityRequest;
import net.kemitix.naolo.core.jpa.AddEntityUseCase;
import net.kemitix.naolo.core.jpa.EntityRepository;

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
