package net.kemitix.naolo.core.vets;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.AddEntityRequest;
import net.kemitix.naolo.core.AddEntityUseCase;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
@RequiredArgsConstructor
public class AddVet
        implements AddEntityUseCase<Veterinarian> {

    @Getter
    private final EntityRepository<Veterinarian> repository;

    public static AddEntityRequest<Veterinarian> request(
            final Veterinarian veterinarian
    ) {
        return AddEntityRequest.create(veterinarian);
    }
}
