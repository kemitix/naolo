package net.kemitix.naolo.core.owners;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.UseCase;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.OwnerRepository;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
@RequiredArgsConstructor
public class ListAllOwners
        implements UseCase<ListAllOwners.Request, ListAllOwners.Response> {
    private static final Request REQUEST = new Request();
    private final OwnerRepository repository;

    public static Request request() {
        return REQUEST;
    }

    @Override
    public Response invoke(final Request request) {
        return Response.builder()
                .owners(
                        repository.findAll()
                                .collect(Collectors.toList())
                )
                .build();
    }

    public static class Request {
        private Request() {
        }
    }

    @Builder
    @Getter
    public static class Response {
        private final List<Owner> owners;
    }
}
