package net.kemitix.naolo.entities;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.ZonedDateTime;

@Value
@Builder
@With
public class Pet {
    Long id;
    String name;
    ZonedDateTime dateOfBirth;
    PetType type;
    Owner owner;
}
