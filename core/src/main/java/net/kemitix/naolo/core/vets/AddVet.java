package net.kemitix.naolo.core.vets;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.java.Log;
import net.kemitix.naolo.core.UseCase;
import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.storage.spi.VeterinarianRepository;

import javax.enterprise.context.Dependent;

@Log
@Dependent
public class AddVet
        implements UseCase<AddVet.Request, AddVet.Response> {
    private final VeterinarianRepository repository;

    public AddVet(final VeterinarianRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response invoke(final Request request) {
        return Response.builder()
                .veterinarian(
                        repository.add(
                                request.veterinarian))
                .build();
    }

    @Builder
    public static class Request {
        private final Veterinarian veterinarian;
    }

    @Getter
    @Builder
    public static class Response {
        private final Veterinarian veterinarian;
    }
}
