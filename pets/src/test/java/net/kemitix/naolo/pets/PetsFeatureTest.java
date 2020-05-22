package net.kemitix.naolo.pets;

import net.kemitix.naolo.core.ui.NavigationItem;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.enterprise.inject.Instance;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PetsFeatureTest
        implements WithAssertions {

    private final Instance<NavigationItem> items;
    private final NavigationItem item = new ManagePets();

    public PetsFeatureTest(
            @Mock final Instance<NavigationItem> items
    ) {
        this.items = items;
    }

    @Test
    @DisplayName("Validate Values")
    public void validate() {
        //given
        given(items.stream()).willReturn(Stream.of(item));
        final PetsFeature feature = new PetsFeature(items);
        //then
        assertThat(feature)
                .extracting("name", "description",
                        "slug", "weight")
                .containsExactly("pets", "Pets",
                        "pets", 10);
        assertThat(feature.getNavigationItems())
                .containsExactly(item);
    }
}
