package net.kemitix.naolo.entities;


import lombok.*;

@With
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Owner {
    Long id;
    String firstName;
    String lastName;
    String street;
    String city;
}
