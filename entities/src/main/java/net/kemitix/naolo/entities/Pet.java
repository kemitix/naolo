package net.kemitix.naolo.entities;

import lombok.*;

import java.time.ZonedDateTime;

@With
@Builder
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class Pet {
    Long id;
    String name;
    ZonedDateTime dateOfBirth;
    PetType type;
    Owner owner;
}
