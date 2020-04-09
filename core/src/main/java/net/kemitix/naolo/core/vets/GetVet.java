package net.kemitix.naolo.core.vets;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.UseCase;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class GetVet
        implements UseCase<GetVet.Request, GetVet.Response> {

    private final VeterinarianRepository repository;

    @Override
    public Response invoke(final Request request) {
        return Response.builder()
                .veterinarian(
                        repository.find(request.id))
                .build();
    }

    @Builder
    public static class Request{
        private final long id;
    }

    @Getter
    @Builder
    public static class Response {
        private final Optional<Veterinarian> veterinarian;
    }
}
