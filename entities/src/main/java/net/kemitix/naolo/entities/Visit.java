package net.kemitix.naolo.entities;


import lombok.*;

import javax.persistence.EntityListeners;
import java.time.ZonedDateTime;

@EntityListeners({
        JPAActivityListener.class
})
@With
@Builder
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class Visit {
    Long id;
    Pet pet;
    Veterinarian veterinarian;
    ZonedDateTime dateTime;
    String description;
}
