package net.kemitix.naolo.storage.spi;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "veterinarian")
public class VeterinarianJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String specialisations;

}
