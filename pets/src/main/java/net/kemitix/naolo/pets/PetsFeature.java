package net.kemitix.naolo.pets;

import net.kemitix.naolo.core.ui.Feature;
import net.kemitix.naolo.core.ui.NavigationItem;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PetsFeature
        implements Feature {
    private final Instance<NavigationItem> navigationItems;

    @Inject
    public PetsFeature(
            @Pets final Instance<NavigationItem> navigationItems
    ) {
        this.navigationItems = navigationItems;
    }

    @Override
    public String getName() {
        return "pets";
    }

    @Override
    public String getDescription() {
        return "Pets";
    }

    @Override
    public String getSlug() {
        return "pets";
    }

    @Override
    public int getWeight() {
        return 10;
    }

    @Override
    public List<NavigationItem> getNavigationItems() {
        return navigationItems.stream()
                .sorted(Comparator.comparingInt(NavigationItem::getWeight))
                .collect(Collectors.toList());
    }
}
