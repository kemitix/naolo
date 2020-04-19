package net.kemitix.naolo.core.visits;

import lombok.Getter;
import net.kemitix.naolo.core.ListEntityRequest;
import net.kemitix.naolo.core.ListEntityUseCase;
import net.kemitix.naolo.entities.Visit;
import net.kemitix.naolo.storage.spi.EntityRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ListVisit
        implements ListEntityUseCase<Visit> {

    @Getter
    private final EntityRepository<Visit> repository;

    @Inject
    public ListVisit(final EntityRepository<Visit> repository) {
        this.repository = repository;
    }

    @Override
    public ListEntityRequest<Visit> request() {
        return ListEntityRequest.create();
    }

}
