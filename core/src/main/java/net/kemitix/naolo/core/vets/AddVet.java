package net.kemitix.naolo.core.vets;

import lombok.Builder;
import lombok.Getter;
import lombok.With;
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
        final Veterinarian veterinarian = request.getVeterinarian();
        log.info("in : " + veterinarian.toString());
        final Veterinarian added = repository.add(veterinarian);
        log.info("out: " + added);
        return Response.builder()
                .veterinarian(added)
                .build();
    }

    @Getter
    @With
    @Builder
    public static class Request {
        Veterinarian veterinarian;
    }

    @Getter
    @With
    @Builder
    public static class Response {
        Veterinarian veterinarian;
    }
}
