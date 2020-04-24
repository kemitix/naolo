package net.kemitix.naolo.pets;

import net.kemitix.naolo.core.jpa.EntityRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RemovePetTest
        implements WithAssertions {

    private final EntityRepository<Pet> repository;
    private final RemovePet removePet;
    private final long id = new Random().nextLong();

    public RemovePetTest(@Mock final EntityRepository<Pet> repository) {
        this.repository = repository;
        removePet = new RemovePet(repository);
    }

    @Test
    @DisplayName("Remove a Pet")
    public void removePet() {
        //given
        final Pet pet = new Pet();
        given(repository.remove(id)).willReturn(Optional.of(pet));
        final var request = removePet.request(id);
        //when
        final var response = removePet.invoke(request);
        //then
        assertThat(response.getEntity()).contains(pet);
        verify(repository).remove(id);
    }

    @Test
    @DisplayName("Remove a Pet that does not exist")
    public void removeMissingPet() {
        //given
        given(repository.remove(id)).willReturn(Optional.empty());
        final var request = removePet.request(id);
        //when
        final var response = removePet.invoke(request);
        //then
        assertThat(response.getEntity()).isEmpty();
        verify(repository).remove(id);
    }
}
