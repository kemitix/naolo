package net.kemitix.naolo.storage.spi;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vetSeq")
    private Long id;
    private String name;
    private String specialisations;

}
