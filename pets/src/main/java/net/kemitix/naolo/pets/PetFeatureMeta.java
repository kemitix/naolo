package net.kemitix.naolo.pets;

import lombok.Getter;
import net.kemitix.naolo.core.FeatureMeta;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@Getter
@ApplicationScoped
public class PetFeatureMeta
        implements FeatureMeta<Pet> {

    private final String name = "Pet";
    private final String endpoint = "/pets";
    private final List<Field<?>> fields = Arrays.asList(
            NumberField.builder()
                    .name("id")
                    .label("Id")
                    .generated(true)
                    .build(),
            TextField.builder()
                    .name("name")
                    .label("Name")
                    .order(10)
                    .build(),
            DateField.builder()
                    .name("date-of-birth")
                    .label("Date of Birth")
                    .order(40)
                    .build(),
            EnumField.builder()
                    .name("pet-type")
                    .label("Pet Type")
                    .values(Arrays.asList("Dog", "Cat"))
                    .order(30)
                    .build(),
            KeyField.builder()
                    .name("owner-id")
                    .label("Owner")
                    .target("owners")
                    .order(20)
                    .build()
    );

}
