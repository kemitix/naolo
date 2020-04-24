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
public class GetEntityTest
        implements WithAssertions {

    private final EntityRepository<Example> repository;
    private final GetEntityUseCase<Example> useCase;

    public GetEntityTest(@Mock final EntityRepository<Example> repository) {
        this.repository = repository;
        useCase = new ExampleUseCase(repository);
    }

    @Test
    @DisplayName("Get Request and Use Case")
    public void getRequestAndUseCase() {
        //given
        final Example example = new Example();
        given(repository.find(example.id)).willReturn(Optional.of(example));
        final GetEntityRequest<Example> request = GetEntityRequest.create(example.id);
        //when
        final var result = useCase.invoke(request);
        //then
        assertThat(result.getEntity()).isEqualTo(Optional.of(example));
    }

    private static class Example implements HasId {
        @Getter
        private final long id = new Random().nextLong();
    }

    private static class ExampleUseCase
            implements GetEntityUseCase<Example> {

        @Getter
        private final EntityRepository<Example> repository;

        private ExampleUseCase(final EntityRepository<Example> repository) {
            this.repository = repository;
        }

        @Override
        public GetEntityRequest<Example> request(final long id) {
            return GetEntityRequest.create(id);
        }
    }
}
