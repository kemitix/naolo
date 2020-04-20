package net.kemitix.naolo.war;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NaoloAppTest
        implements WithAssertions {

    @Test
    @DisplayName("Can Instantiate")
    public void canInstantiate() {
        assertThatCode(NaoloApp::new)
                .doesNotThrowAnyException();
    }
}