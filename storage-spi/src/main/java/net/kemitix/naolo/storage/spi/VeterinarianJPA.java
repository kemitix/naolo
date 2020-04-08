package net.kemitix.naolo.storage.spi;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "veterinarian")
public class VeterinarianJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String specialisations;

}
