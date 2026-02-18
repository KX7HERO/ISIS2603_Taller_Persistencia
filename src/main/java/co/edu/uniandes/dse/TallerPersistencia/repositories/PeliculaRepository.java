package co.edu.uniandes.dse.TallerPersistencia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.TallerPersistencia.entities.PeliculaEntity;

/**
 * Repositorio para la entidad PeliculaEntity
 */
@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long> {
    
    /**
     * Busca una película por su título
     * @param titulo el título de la película
     * @return la película encontrada o null
     */
    PeliculaEntity findByTitulo(String titulo);
}
