package net.kemitix.naolo.core.owners;

import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.OwnerRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddOwnerTest
        implements WithAssertions {

    @Test
    @DisplayName("Add an Owner")
    public void addOwners() {
        //given
        final OwnerRepository repository = mock(OwnerRepository.class);
        final AddOwner addOwner = new AddOwner(repository);
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
        given(repository.add(owner))
                .willAnswer(call ->
                        ((Owner) call.getArgument(0))
                                .withId(nextId));
        final AddOwner.Request request =
                AddOwner.Request.builder()
                        .owner(owner)
                        .build();
        //when
        final AddOwner.Response response = addOwner.invoke(request);
        //then
        assertThat(response.getOwner().getId()).isEqualTo(nextId);
        verify(repository).add(owner);
    }
}