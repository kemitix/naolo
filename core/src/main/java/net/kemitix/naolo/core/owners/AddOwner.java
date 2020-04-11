package net.kemitix.naolo.core.owners;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.UseCase;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.OwnerRepository;

import javax.enterprise.context.Dependent;

@Dependent
@RequiredArgsConstructor
public class AddOwner
        implements UseCase<AddOwner.Request, AddOwner.Response> {
    private final OwnerRepository repository;

    @Override
    public Response invoke(final Request request) {
        return Response.builder()
                .owner(
                        repository.add(
                                request.owner))
                .build();
    }

    @Builder
    public static class Request {
        private final Owner owner;
    }

    @Builder
    @Getter
    public static class Response {
        private final Owner owner;
    }
}
