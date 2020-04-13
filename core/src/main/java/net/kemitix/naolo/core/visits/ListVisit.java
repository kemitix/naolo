package net.kemitix.naolo.core.visits;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kemitix.naolo.core.ListEntityRequest;
import net.kemitix.naolo.core.ListEntityUseCase;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Getter
@RequiredArgsConstructor
public class ListVisit
        implements ListEntityUseCase<Visit> {

    private final EntityRepository<Visit> repository;

    @Override
    public ListEntityRequest<Visit> request() {
        return ListEntityRequest.create();
    }

}
