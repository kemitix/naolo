package net.kemitix.naolo.core.jpa;

import lombok.Getter;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EntityRepositoryTest
        implements WithAssertions {

    private final EntityManager entityManager;
    private final ExampleRepository repository;
    private final TypedQuery<Example> queryResult;

    public EntityRepositoryTest(
            @Mock final EntityManager entityManager,
            @Mock final TypedQuery<Example> queryResult) {
        this.entityManager = entityManager;
        this.queryResult = queryResult;
        repository = new ExampleRepository();
    }

    @Test
    @DisplayName("Has Required No-Args Constructor")
    public void hasRequiredNoArgsConstructor() {
        assertThatCode(ExampleRepository::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("add() merges the entity")
    public void addMergesTheEntity() {
        //given
        final Example example = new Example();
        given(entityManager.merge(example)).willReturn(example);
        //when
        final Example result = repository.add(example);
        //then
        assertThat(result).isEqualTo(example);
    }

    @Test
    @DisplayName("find() finds the entity")
    public void findFinds() {
        //given
        final Example example = new Example();
        given(entityManager.find(Example.class, example.id))
                .willReturn(example);
        //when
        final Optional<Example> result = repository.find(example.id);
        //then
        assertThat(result).contains(example);
    }

    @Test
    @DisplayName("findAll() calls getResultStream on Query result")
    public void findAllCallsResultStreamOnQuery() {
        //given
        given(entityManager.createNamedQuery(Example.FindAll, Example.class))
                .willReturn(queryResult);
        final Example example1 = new Example();
        final Example example2 = new Example();
        given(queryResult.getResultStream())
                .willReturn(Stream.of(example1, example2));
        //when
        final Stream<Example> result = repository.findAll();
        //then
        assertThat(result).containsExactly(example1, example2);
    }

    @Test
    @DisplayName("remove() finds record then removes it")
    public void removeFindsThenRemoves() {
        //given
        final Example example = new Example();
        given(entityManager.find(Example.class, example.id))
                .willReturn(example);
        //when
        final Optional<Example> result = repository.remove(example.id);
        //then
        assertThat(result).contains(example);
    }

    @Test
    @DisplayName("update() finds record then merges it")
    public void updateFindsThenMerges() {
        //given
        final Example example = new Example();
        given(entityManager.find(Example.class, example.id))
                .willReturn(example);
        given(entityManager.merge(example)).willReturn(example);
        //when
        final Optional<Example> result = repository.update(example);
        //then
        assertThat(result).contains(example);
    }

    private static class Example implements HasId {
        public static String FindAll = "Example.FindAll";
        @Getter
        private final long id = new Random().nextLong();
    }

    private class ExampleRepository
            extends AbstractEntityRepository<Example> {
        private ExampleRepository() {
            super(entityManager);
        }

        @Override
        public Stream<Example> findAll() {
            return findAll(Example.FindAll, Example.class);
        }

        @Override
        public Optional<Example> find(final long id) {
            return find(id, Example.class);
        }

        @Override
        public Optional<Example> update(final Example example) {
            return update(example, Example.class);
        }
    }
}
