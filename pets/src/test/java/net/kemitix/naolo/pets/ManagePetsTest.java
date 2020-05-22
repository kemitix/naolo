package net.kemitix.naolo.pets;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManagePetsTest
        implements WithAssertions {
    @Test
    @DisplayName("Validate Values")
    public void validate() {
        //given
        final ManagePets managePets = new ManagePets();
        //then
        assertThat(managePets)
                .extracting("name",
                        "description", "slug",
                        "weight", "enabled",
                        "enabledIcon",
                        "disabledIcon")
                .containsExactly("Pets",
                        "Add, update, remove Pets", "manage",
                        10, true,
                        "pets/manage-enabled.png",
                        "pets/manage-disabled.png");
    }
}
