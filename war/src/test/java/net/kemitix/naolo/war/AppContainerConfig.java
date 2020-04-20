package net.kemitix.naolo.war;

import org.microshed.testing.SharedContainerConfiguration;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.junit.jupiter.Container;

public class AppContainerConfig
        implements SharedContainerConfiguration {

    @Container
    public static ApplicationContainer app =
            new ApplicationContainer()
                    .withAppContextRoot("/naolo-war-DEV-SNAPSHOT")
                    .withReadinessPath("/naolo-war-DEV-SNAPSHOT/naolo/pets");

}
