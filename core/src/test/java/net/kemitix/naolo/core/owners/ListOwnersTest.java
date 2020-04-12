package net.kemitix.naolo.core.owners;

import net.kemitix.naolo.core.StreamZipper;
import net.kemitix.naolo.core.Tuple;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.OwnerRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ListAllOwnersTest
        implements WithAssertions {

    private final OwnerRepository repository =
            mock(OwnerRepository.class);
    private final ListAllOwners listAllOwners =
            new ListAllOwners(repository);

    @Test
    @DisplayName("List All Owners")
    public void listAllOwners() {
        //given
        final List<Owner> owners = Arrays.asList(
                new Owner().withId(43L).withFirstName("bob")
                        .withLastName("smith").withStreet("street 1")
                        .withCity("city 1"),
                new Owner().withId(23L).withFirstName("sam")
                        .withLastName("sykes").withStreet("street 2")
                        .withCity("city 2")
        );
        given(repository.findAll()).willReturn(owners.stream());
        final ListAllOwners.Request request = ListAllOwners.request();
        //when
        final ListAllOwners.Response response = listAllOwners.invoke(request);
        //then
        final Stream<Tuple<Owner, Owner>> zipped =
                StreamZipper.zip(owners, response.getOwners(), Tuple::of);
        assertThat(zipped).hasSize(owners.size())
        .allSatisfy(t -> {
            final Owner expected = t.get1();
            final Owner result = t.get2();
            assertThat(expected).isEqualTo(result);
        });
    }
}