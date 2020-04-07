package net.kemitix.naolo.entities;


import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class Owner {
    Long id;
    String firstName;
    String lastName;
    String street;
    String city;
}
