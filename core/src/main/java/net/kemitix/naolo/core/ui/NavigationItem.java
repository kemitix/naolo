package net.kemitix.naolo.core.ui;

public interface NavigationItem {
    String getName();
    String getDescription();
    String getSlug();
    int getWeight();
    boolean isEnabled();
    String getEnabledIcon();
    String getDisabledIcon();
}
