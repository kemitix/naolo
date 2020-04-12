package net.kemitix.naolo.core.vets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.GetEntityRequest;
import net.kemitix.naolo.core.GetEntityResponse;
import net.kemitix.naolo.core.GetEntityUseCase;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
@RequiredArgsConstructor
public class GetVet
        implements GetEntityUseCase<Veterinarian> {

    @Getter
    private final EntityRepository<Veterinarian> repository;

    public static GetEntityRequest<Veterinarian> request(final long id) {
        return new GetEntityRequest<>(id);
    }

    @Override
    public GetEntityResponse<Veterinarian> invoke(
            final GetEntityRequest<Veterinarian> request
    ) {
        return () -> repository.find(request.getId());
    }

}
