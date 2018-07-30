package net.kemitix.naolo.run.spring;

import net.kemitix.naolo.core.UseCase;
import net.kemitix.naolo.core.VeterinarianRepository;
import net.kemitix.naolo.core.VeterinariansListAll;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class UseCasesTest implements WithAssertions {

    private final UseCases useCases = new UseCases();

    private final VeterinarianRepository veterinarianRepository = mock(VeterinarianRepository.class);

    @Test
    void hasListAllVeterinariansUseCase() {
        //given
        final VeterinariansListAll useCase = useCases.veterinariansListAll(veterinarianRepository);
        //then
        assertThat(useCase).isInstanceOf(UseCase.class);
    }
}