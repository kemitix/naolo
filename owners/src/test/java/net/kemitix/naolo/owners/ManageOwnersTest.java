package net.kemitix.naolo.owners;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManageOwnersTest
        implements WithAssertions {
    @Test
    @DisplayName("Validate Values")
    public void validate() {
        //given
        final ManageOwners manageOwners = new ManageOwners();
        //then
        assertThat(manageOwners)
                .extracting("name",
                        "description", "slug",
                        "weight", "enabled",
                        "enabledIcon",
                        "disabledIcon")
                .containsExactly("Manage Owners",
                        "Add, update, remove Owners", "manage",
                        30, true,
                        "owners/manage-enabled.png",
                        "owners/manage-disabled.png");
    }
}