package net.kemitix.naolo.entities;

import lombok.*;

import java.util.Set;

@With
@Builder
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class Veterinarian {
    Long id;
    String name;
    Set<VetSpecialisation> specialisations;
}
