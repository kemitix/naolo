package net.kemitix.naolo.entities;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Set;

@With
@Value
@Builder
public class Veterinarian {
    Long id;
    String name;
    Set<VetSpecialisation> specialisations;
}
