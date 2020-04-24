package net.kemitix.naolo.owners;


import lombok.*;
import net.kemitix.naolo.entities.JPAActivityListener;
import net.kemitix.naolo.storage.HasId;

import javax.persistence.*;

@EntityListeners({
        JPAActivityListener.class
})
@Entity
@NamedQuery(name = Owner.FIND_ALL,
        query = "Select o from Owner o order by o.lastName, o.firstName")
@With
@Setter
@Getter
@ToString
@EqualsAndHashCode
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

    public Owner() {
    }

    public Owner(
            final long id,
            final String firstName,
            final String lastName,
            final String street,
            final String city
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
    }
}
