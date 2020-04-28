package net.kemitix.naolo.owners;

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
public class OwnersFeatureTest
        implements WithAssertions {

    private final Instance<NavigationItem> items;
    private final NavigationItem item = new ManageOwners();

    public OwnersFeatureTest(
            @Mock final Instance<NavigationItem> items
    ) {
        this.items = items;
    }

    @Test
    @DisplayName("Validate Values")
    public void validate() {
        //given
        given(items.stream()).willReturn(Stream.of(item));
        final OwnersFeature feature = new OwnersFeature(items);
        //then
        assertThat(feature)
                .extracting("name", "description",
                        "slug", "weight")
                .containsExactly("Owners", "Manage pet owners",
                        "owners", 20);
        assertThat(feature.getNavigationItems())
                .containsExactly(item);
    }
}
