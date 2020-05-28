package net.kemitix.naolo.owners;

import lombok.Getter;
import net.kemitix.naolo.core.FeatureMeta;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@Getter
@ApplicationScoped
public class OwnersFeatureMeta
        implements FeatureMeta<Owner> {

    private final String name = "Owners";
    private final String endpoint = "/owners";
    private final List<Field<?>> fields = Arrays.asList(
            NumberField.builder()
                    .name("id")
                    .label("Id")
                    .generated(true)
                    .build(),
            TextField.builder()
                    .name("surname")
                    .label("Surname")
                    .order(10)
                    .build(),
            TextField.builder()
                    .name("forename")
                    .label("Forename")
                    .order(20)
                    .build(),
            TextField.builder()
                    .name("street")
                    .label("Street")
                    .order(30)
                    .build(),
            TextField.builder()
                    .name("city")
                    .label("City")
                    .order(40)
                    .build()
    );
}
