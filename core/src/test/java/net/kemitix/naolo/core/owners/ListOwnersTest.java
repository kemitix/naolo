package net.kemitix.naolo.core.owners;

import net.kemitix.naolo.core.StreamZipper;
import net.kemitix.naolo.core.Tuple;
import net.kemitix.naolo.entities.Owner;
import net.kemitix.naolo.storage.spi.EntityRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ListOwnersTest
        implements WithAssertions {

    private final EntityRepository<Owner> repository;
    private final ListOwners listOwners;

    public ListOwnersTest(@Mock final EntityRepository<Owner> repository) {
        this.repository = repository;
        listOwners = new ListOwners(repository);
    }

    @Test
    @DisplayName("List Owners")
    public void listOwners() {
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
        final var request = listOwners.request();
        //when
        final var response = listOwners.invoke(request);
        //then
        final Stream<Tuple<Owner, Owner>> zipped =
                StreamZipper.zip(owners, response.getEntities(), Tuple::of);
        assertThat(zipped)
                .hasSize(owners.size())
                .allSatisfy(t -> {
                    final Owner expected = t.get1();
                    final Owner result = t.get2();
                    assertThat(expected).isEqualTo(result);
                });
    }
}