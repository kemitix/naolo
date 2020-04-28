package net.kemitix.naolo.core.ui;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.enterprise.inject.Instance;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class FeatureResourceTest
        implements WithAssertions {

    public static final String FEATURE_SLUG = "test-feature-slug";
    public static final String FEATURE_DESCRIPTION = "test-feature-description";
    public static final String FEATURE_NAME = "test-feature-name";
    public static final String DISABLED_ICON = "test-disabled-icon";
    public static final String ENABLED_ICON = "test-enabled-icon";
    public static final int ITEM_WEIGHT = 200;
    public static final String ITEM_SLUG = "test-item-slug";
    public static final String ITEM_DESCRIPTION = "test-item-description";
    public static final String ITEM_NAME = "test-item-name";
    public static final int FEATURE_WEIGHT = 300;
    private final Instance<Feature> featureInstances;
    private final List<NavigationItem> navigationItems =
            Collections.singletonList(new TestNavigationItem());
    private final Feature feature = new TestFeature(navigationItems);

    public FeatureResourceTest(@Mock final Instance<Feature> featureInstances) {
        this.featureInstances = featureInstances;
    }

    @Test
    @DisplayName("Get a list of features")
    public void getListOfFeatures() {
        //given
        given(featureInstances.stream())
                .willReturn(Stream.of(feature));
        //when
        final List<Feature> result =
                new FeatureResource(featureInstances).features();
        //then
        assertThat(result).containsExactly(feature);
    }


    private static class TestNavigationItem
            implements NavigationItem {
        @Override
        public String getName() {
            return ITEM_NAME;
        }

        @Override
        public String getDescription() {
            return ITEM_DESCRIPTION;
        }

        @Override
        public String getSlug() {
            return ITEM_SLUG;
        }

        @Override
        public int getWeight() {
            return ITEM_WEIGHT;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public String getEnabledIcon() {
            return ENABLED_ICON;
        }

        @Override
        public String getDisabledIcon() {
            return DISABLED_ICON;
        }
    }

    private static class TestFeature implements Feature {
        private final List<NavigationItem> navigationItems;

        public TestFeature(final List<NavigationItem> navigationItems) {
            this.navigationItems = navigationItems;
        }

        @Override
        public String getName() {
            return FEATURE_NAME;
        }

        @Override
        public String getDescription() {
            return FEATURE_DESCRIPTION;
        }

        @Override
        public String getSlug() {
            return FEATURE_SLUG;
        }

        @Override
        public int getWeight() {
            return FEATURE_WEIGHT;
        }

        @Override
        public List<NavigationItem> getNavigationItems() {
            return navigationItems;
        }
    }
}
