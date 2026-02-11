package co.edu.uniandes.dse.TallerPersistencia.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad que representa una Pelicula.
 */
@Data
@Entity
@Table(name = "Pelicula")
@EqualsAndHashCode(callSuper = true)
public class PeliculaEntity extends BaseEntity {

    private String titulo;

    private Integer anioLanzamiento;

    @PodamExclude
    @ManyToMany
    @JoinTable(name = "Actor_Pelicula",
        joinColumns = @JoinColumn(name = "pelicula_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<ActorEntity> actores = new ArrayList<>();

    @PodamExclude
    @ManyToOne
    @JoinColumn(name = "director_id")
    private DirectorEntity director;

}
