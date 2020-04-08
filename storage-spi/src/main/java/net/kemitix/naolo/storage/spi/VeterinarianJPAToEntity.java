package net.kemitix.naolo.storage.spi;

import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;

import javax.enterprise.context.Dependent;
import java.util.function.Function;

@Dependent
public class VeterinarianJPAToEntity
        implements Function<VeterinarianJPA, Veterinarian> {
    @Override
    public Veterinarian apply(final VeterinarianJPA jpa) {
        return Veterinarian.builder()
                .id(jpa.getId())
                .name(jpa.getName())
                .specialisations(
                        VetSpecialisation.parse(
                                jpa.getSpecialisations()))
                .build();
    }
}
