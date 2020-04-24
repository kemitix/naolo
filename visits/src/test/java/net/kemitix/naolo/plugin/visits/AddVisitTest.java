package net.kemitix.naolo.plugin.visits;

import net.kemitix.naolo.entities.Veterinarian;
import net.kemitix.naolo.pets.Pet;
import net.kemitix.naolo.storage.EntityRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddVisitTest
        implements WithAssertions {

    private final EntityRepository<Visit> repository;
    private final AddVisit addVisit;

    public AddVisitTest(@Mock final EntityRepository<Visit> repository) {
        this.repository = repository;
        addVisit = new AddVisit(repository);
    }

    @Test
    @DisplayName("Add a Visit")
    public void addVisit() {
        //given
        final String name = "name";
        final Pet pet = new Pet();
        final Veterinarian veterinarian = new Veterinarian();
        final LocalDateTime dateTime = LocalDateTime.now();
        final String description = "description";
        final Visit visit =
                new Visit()
                        .withPet(pet)
                        .withVeterinarian(veterinarian)
                        .withDateTime(dateTime)
                        .withDescription(description);
        final long nextId = 42;
        given(repository.add(visit)).willReturn(visit.withId(nextId));
        final var request = addVisit.request(visit);
        //when
        final var response = addVisit.invoke(request);
        //then
        assertThat(response.getEntity().getId()).isEqualTo(nextId);
        verify(repository).add(visit);
    }
}