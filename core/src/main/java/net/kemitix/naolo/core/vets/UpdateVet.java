package net.kemitix.naolo.core.vets;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.UseCase;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;

import javax.enterprise.context.Dependent;
import java.util.Optional;

@Dependent
@RequiredArgsConstructor
public class UpdateVet
        implements UseCase<UpdateVet.Request, UpdateVet.Response> {
    private final VeterinarianRepository repository;

    @Override
    public Response invoke(final Request request) {
        return Response.builder()
                .veterinarian(
                        repository.update(
                                request.veterinarian))
                .build();
    }

    @Builder
    public static class Request {
        public Veterinarian veterinarian;
    }

    @Getter
    @Builder
    public static class Response {
        private final Optional<Veterinarian> veterinarian;
    }
}
