package net.kemitix.naolo.entities;


import lombok.*;

@With
@Builder
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class Owner {
    Long id;
    String firstName;
    String lastName;
    String street;
    String city;
}
