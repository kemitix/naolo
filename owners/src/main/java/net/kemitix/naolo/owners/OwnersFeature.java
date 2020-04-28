package net.kemitix.naolo.owners;

import net.kemitix.naolo.core.ui.Feature;
import net.kemitix.naolo.core.ui.NavigationItem;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OwnersFeature
        implements Feature {

    private final Instance<NavigationItem> navigationItems;

    @Inject
    public OwnersFeature(
            @Owners final Instance<NavigationItem> navigationItems
    ) {
        this.navigationItems = navigationItems;
    }

    @Override
    public String getName() {
        return "Owners";
    }

    @Override
    public String getDescription() {
        return "Manage pet owners";
    }

    @Override
    public String getSlug() {
        return "owners";
    }

    @Override
    public int getWeight() {
        return 20;
    }

    @Override
    public List<NavigationItem> getNavigationItems() {
        return navigationItems.stream().collect(Collectors.toList());
    }
}
