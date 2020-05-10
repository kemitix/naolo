package net.kemitix.naolo.owners;

import net.kemitix.naolo.core.ui.NavigationItem;

import javax.enterprise.context.ApplicationScoped;

@Owners
@ApplicationScoped
public class ManageOwners
        implements NavigationItem {
    @Override
    public String getName() {
        return "Manage Owners";
    }

    @Override
    public String getDescription() {
        return "Add, update, remove Owners";
    }

    @Override
    public String getSlug() {
        return "manage";
    }

    @Override
    public int getWeight() {
        return 30;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getEnabledIcon() {
        return "owners/manage-enabled.png";
    }

    @Override
    public String getDisabledIcon() {
        return "owners/manage-disabled.png";
    }
}
