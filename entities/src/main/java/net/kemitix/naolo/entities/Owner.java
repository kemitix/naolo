package net.kemitix.naolo.entities;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = Owner.FIND_ALL,
        query = "Select o from Owner o order by o.lastName, o.firstName")
@With
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Owner
        implements HasId {

    public static final String FIND_ALL = "Owner.FindAll";

    @Id
    @GeneratedValue
    long id;
    String firstName;
    String lastName;
    String street;
    String city;
}
