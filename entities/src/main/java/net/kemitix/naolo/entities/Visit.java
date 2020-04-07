package net.kemitix.naolo.entities;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.ZonedDateTime;

@Value
@Builder
@With
public class Visit {
    Long id;
    Pet pet;
    Veterinarian veterinarian;
    ZonedDateTime dateTime;
    String description;
}
