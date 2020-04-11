package net.kemitix.naolo.core.owners;

import net.kemitix.naolo.core.AddEntityRequest;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.OwnerRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddOwnerTest
        implements WithAssertions {

    private final OwnerRepository repository;
    private final AddOwner addOwner;

    public AddOwnerTest(@Mock final OwnerRepository repository) {
        this.repository = repository;
        addOwner = new AddOwner(repository);
    }

    @Test
    @DisplayName("Add an Owner")
    public void addOwner() {
        //given
        final String firstName = "first name";
        final String lastName = "last name";
        final String city = "city";
        final String street = "street";
        final Owner owner =
                new Owner()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withCity(city)
                        .withStreet(street);
        final long nextId = 42;
        given(repository.add(owner)).willReturn(owner.withId(nextId));
        final var request = AddEntityRequest.create(owner);
        //when
        final var response = addOwner.invoke(request);
        //then
        assertThat(response.getEntity().getId()).isEqualTo(nextId);
        verify(repository).add(owner);
    }
}