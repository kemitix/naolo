package net.kemitix.naolo.storage.spi;

import net.kemitix.naolo.entities.VetSpecialisation;
import net.kemitix.naolo.entities.Veterinarian;

import javax.enterprise.context.Dependent;
import java.util.function.Function;
import java.util.stream.Collectors;

@Dependent
public class VeterinarianEntityToJPA
        implements Function<Veterinarian, VeterinarianJPA> {
    @Override
    public VeterinarianJPA apply(final Veterinarian entity) {
        final VeterinarianJPA jpa = new VeterinarianJPA();
        jpa.setId(entity.getId());
        jpa.setName(entity.getName());
        jpa.setSpecialisations(entity.getSpecialisations().stream()
                .map(VetSpecialisation::name)
                .collect(Collectors.joining(";")));
        return jpa;
    }
}
