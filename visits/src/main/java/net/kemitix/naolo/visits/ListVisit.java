package net.kemitix.naolo.visits;

import lombok.Getter;
import net.kemitix.naolo.core.jpa.EntityRepository;
import net.kemitix.naolo.core.jpa.ListEntityRequest;
import net.kemitix.naolo.core.jpa.ListEntityUseCase;

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
