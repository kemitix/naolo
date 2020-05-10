package net.kemitix.naolo.core.ui;

import java.util.List;

public interface Feature {
    String getName();
    String getDescription();
    String getSlug();
    int getWeight();
    List<NavigationItem> getNavigationItems();
}
