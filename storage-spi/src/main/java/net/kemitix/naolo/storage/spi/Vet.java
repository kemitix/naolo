package net.kemitix.naolo.storage.spi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vetSeq")
    private Long id;
    private String name;
    private String specialisations;

    public Vet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSpecialisations() {
        return specialisations;
    }

    public void setSpecialisations(final String specialisations) {
        this.specialisations = specialisations;
    }
}
