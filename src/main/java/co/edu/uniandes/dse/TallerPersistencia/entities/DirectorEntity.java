package co.edu.uniandes.dse.TallerPersistencia.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad que representa un Director.
 */
@Data
@Entity
@Table(name = "Director")
@EqualsAndHashCode(callSuper = true)
public class DirectorEntity extends BaseEntity {

    private String nombre;

    private String biografia;

    @PodamExclude
    @OneToMany(mappedBy = "director")
    private List<PeliculaEntity> peliculas = new ArrayList<>();

}
