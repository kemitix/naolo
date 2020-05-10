package net.kemitix.naolo.pets;

import net.kemitix.naolo.core.ui.NavigationItem;

import javax.enterprise.context.ApplicationScoped;

@Pets
@ApplicationScoped
public class ManagePets
        implements NavigationItem {
    @Override
    public String getName() {
        return "Manage Pets";
    }

    @Override
    public String getDescription() {
        return "Add, update, remove Pets";
    }

    @Override
    public String getSlug() {
        return "manage";
    }

    @Override
    public int getWeight() {
        return 10;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getEnabledIcon() {
        return "pets/manage-enabled.png";
    }

    @Override
    public String getDisabledIcon() {
        return "pets/manage-disabled.png";
    }
}
