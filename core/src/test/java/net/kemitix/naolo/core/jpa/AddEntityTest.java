package net.kemitix.naolo.core.jpa;

import lombok.Getter;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AddEntityTest
        implements WithAssertions {

    private final EntityRepository<Example> repository;
    private final AddEntityUseCase<Example> useCase;

    public AddEntityTest(@Mock final EntityRepository<Example> repository) {
        this.repository = repository;
        useCase = new ExampleUseCase(repository);
    }

    @Test
    @DisplayName("Add Request and Use Case")
    public void AddRequestAndUseCase() {
        //given
        final Example example = new Example();
        given(repository.add(example)).willReturn(example);
        final var request = AddEntityRequest.create(example);
        //when
        final var result = useCase.invoke(request);
        //then
        assertThat(result.getEntity()).isEqualTo(example);
    }

    private static class Example implements HasId {
        @Getter
        private final long id = new Random().nextLong();
    }

    private static class ExampleUseCase
            implements AddEntityUseCase<Example> {

        @Getter
        private final EntityRepository<Example> repository;

        private ExampleUseCase(final EntityRepository<Example> repository) {
            this.repository = repository;
        }

        @Override
        public AddEntityRequest<Example> request(final Example entity) {
            return AddEntityRequest.create(entity);
        }

    }
}
