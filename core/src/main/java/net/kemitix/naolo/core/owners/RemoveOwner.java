package net.kemitix.naolo.core.owners;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.UseCase;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.OwnerRepository;

import javax.enterprise.context.Dependent;
import java.util.Optional;

@Dependent
@RequiredArgsConstructor
public class RemoveOwner
        implements UseCase<RemoveOwner.Request, RemoveOwner.Response> {
    private final OwnerRepository repository;

    @Override
    public Response invoke(final Request request) {
        return Response.builder()
                .owner(
                        repository.remove(request.id))
                .build();
    }

    @Builder
    public static class Request {
        private final long id;
    }

    @Builder
    @Getter
    public static class Response {
        private final Optional<Owner> owner;
    }
}
