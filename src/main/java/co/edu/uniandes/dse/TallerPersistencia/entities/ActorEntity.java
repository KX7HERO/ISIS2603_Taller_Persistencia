package co.edu.uniandes.dse.TallerPersistencia.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad que representa un Actor.
 */
@Data
@Entity
@Table(name = "Actor")
@EqualsAndHashCode(callSuper = true)
public class ActorEntity extends BaseEntity {

    private String nombre;

    private String nacionalidad;

    @PodamExclude
    @ManyToMany(mappedBy = "actores")
    private List<PeliculaEntity> peliculas = new ArrayList<>();

}
