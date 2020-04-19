package net.kemitix.naolo.war;

import net.kemitix.naolo.api.OwnerResource;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.junit.jupiter.Container;

@MicroShedTest
public class OwnersIT
        implements WithAssertions {

    @Container
    public static ApplicationContainer app =
            new ApplicationContainer()
                    .withAppContextRoot("/naolo-war-DEV-SNAPSHOT")
                    .waitingFor(new LogMessageWaitStrategy()
                            .withRegEx("Deployed \"naolo-war-DEV-SNAPSHOT.war\""));

    @RESTClient
    public static OwnerResource ownerResource;

    @Test
    @DisplayName("true is true")
    public void exist() {
        //given
        //...
        //when
        //then
        assertThat(true).isTrue();
    }
}
