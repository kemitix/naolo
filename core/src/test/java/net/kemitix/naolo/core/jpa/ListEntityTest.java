package net.kemitix.naolo.core.jpa;

import lombok.Getter;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Random;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ListEntityTest
        implements WithAssertions {

    private final EntityRepository<Example> repository;
    private final ListEntityUseCase<Example> useCase;

    public ListEntityTest(@Mock final EntityRepository<Example> repository) {
        this.repository = repository;
        useCase = new ExampleUseCase(repository);
    }

    @Test
    @DisplayName("List Request and Use Case")
    public void listRequestAndUseCase() {
        //given
        final var examples = Arrays.asList(new Example(), new Example());
        given(repository.findAll()).willReturn(examples.stream());
        final ListEntityRequest<Example> request = ListEntityRequest.create();
        //when
        final var result = useCase.invoke(request);
        //then
        assertThat(result.getEntities()).isEqualTo(examples);
    }

    private static class Example implements HasId {
        @Getter
        private final long id = new Random().nextLong();
    }

    private static class ExampleUseCase
            implements ListEntityUseCase<Example> {

        @Getter
        private final EntityRepository<Example> repository;

        private ExampleUseCase(final EntityRepository<Example> repository) {
            this.repository = repository;
        }

        @Override
        public ListEntityRequest<Example> request() {
            return ListEntityRequest.create();
        }
    }
}
