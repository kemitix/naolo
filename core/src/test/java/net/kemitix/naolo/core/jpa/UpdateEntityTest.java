package net.kemitix.naolo.core.jpa;

import lombok.Getter;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UpdateEntityTest
        implements WithAssertions {

    private final EntityRepository<Example> repository;
    private final UpdateEntityUseCase<Example> useCase;

    public UpdateEntityTest(@Mock final EntityRepository<Example> repository) {
        this.repository = repository;
        useCase = new ExampleUseCase(repository);
    }

    @Test
    @DisplayName("Update Request and Use Case")
    public void UpdateRequestAndUseCase() {
        //given
        final Example example = new Example();
        given(repository.update(example)).willReturn(Optional.of(example));
        final var request = UpdateEntityRequest.create(example);
        //when
        final var result = useCase.invoke(request);
        //then
        assertThat(result.getEntity()).contains(example);
    }

    private static class Example implements HasId {
        @Getter
        private final long id = new Random().nextLong();
    }

    private static class ExampleUseCase
            implements UpdateEntityUseCase<Example> {

        @Getter
        private final EntityRepository<Example> repository;

        private ExampleUseCase(final EntityRepository<Example> repository) {
            this.repository = repository;
        }

        @Override
        public UpdateEntityRequest<Example> request(final Example entity) {
            return UpdateEntityRequest.create(entity);
        }

    }
}
