/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Paul Campbell
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.kemitix.naolo.gateway.data.jpa;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.kemitix.naolo.entities.Veterinarian;

import javax.persistence.*;
import java.util.Set;

/**
 * JPA Entity for Veterinarian.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
@Entity
@Table(name = "veterinarians")
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQueries({
        @NamedNativeQuery(
                name = VeterinarianJPA.FIND_ALL_VETS,
                query = "SELECT v.id, v.name, "
                        + "IFNULL(GROUP_CONCAT(vs.specialisations_value SEPARATOR ';'), '') specialisations "
                        + "FROM veterinarians v "
                        + "LEFT JOIN veterinarians_vet_specialisations vs ON vs.VeterinarianJPA_id = v.id "
                        + "GROUP BY v.id, v.name "
                        + "ORDER BY v.id",
                resultSetMapping = VeterinarianJPA.VETERINARIAN_RESULT_MAPPER
        )})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = VeterinarianJPA.VETERINARIAN_RESULT_MAPPER,
                classes = @ConstructorResult(
                        targetClass = Veterinarian.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "name"),
                                @ColumnResult(name = "specialisations", type = String.class)
                        }
                )
        )})
class VeterinarianJPA {

    static final String FIND_ALL_VETS = "net.kemitix.naolo.gateway.data.jpa.findAllVets";
    static final String VETERINARIAN_RESULT_MAPPER = "net.kemitix.naolo.gateway.data.jpa.Veterinarian";

    @Id
    @GeneratedValue
    private Long id;

    @SuppressFBWarnings("URF_UNREAD_FIELD")
    private String name;

    @ManyToMany
    private Set<VetSpecialisationJPA> specialisations;

}
