
package co.edu.uniandes.dse.TallerPersistencia.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.TallerPersistencia.entities.PeliculaEntity;
import co.edu.uniandes.dse.TallerPersistencia.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.TallerPersistencia.repositories.PeliculaRepository;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@SpringBootTest
@Transactional
public class PeliculaServiceTest {

	
	@Autowired
	private PeliculaService peliculaService;


	@Autowired
	private PeliculaRepository peliculaRepository;


	private PodamFactory factory;

	 

	@BeforeEach
	public void setUp() {
		factory = new PodamFactoryImpl();
		peliculaRepository.deleteAll();
	}

	@Test
	public void testCrearPeliculaValida() throws IllegalOperationException {
		// Paso 1: Generar datos aleatorios con PODAM
		PeliculaEntity peliculaTest = factory.manufacturePojo(PeliculaEntity.class);

		// Paso 2: Configurar datos para cumplir validaciones
		peliculaTest.setTitulo("Inception");
		peliculaTest.setAnioLanzamiento(2010);

		// Paso 3: Ejecutar el método bajo prueba
		PeliculaEntity resultado = peliculaService.crearPelicula(peliculaTest);

		// Paso 4: Verificar el resultado
		assertNotNull(resultado, "La película creada no debe ser nula");
		assertEquals("Inception", resultado.getTitulo(), "El título debe coincidir");
		assertEquals(2010, resultado.getAnioLanzamiento(), "El año debe coincidir");

		// Paso 5: Validar estado en la base de datos
		long cantidad = peliculaRepository.count();
		assertEquals(1, cantidad, "La película debe estar guardada en la BD");

		PeliculaEntity peliculaGuardada = peliculaRepository.findByTitulo("Inception");
		assertNotNull(peliculaGuardada, "La película debe existir en la BD");
		assertEquals("Inception", peliculaGuardada.getTitulo());
	}

	@Test
	public void testCrearPeliculaConTituloNulo() {
		PeliculaEntity peliculaTest = factory.manufacturePojo(PeliculaEntity.class);

		peliculaTest.setTitulo(null);
		peliculaTest.setAnioLanzamiento(2010);

		IllegalOperationException excepcion = assertThrows(IllegalOperationException.class, () -> {
			peliculaService.crearPelicula(peliculaTest);
		});

		assertTrue(excepcion.getMessage().contains("no puede estar vacío"), 
			"El mensaje debe mencionar que no puede estar vacío");

		// Paso 5: Validar que la película NO se guardó en la BD
		long cantidad = peliculaRepository.count();
		assertEquals(0, cantidad, "La película NO debe guardarse en la BD");
	}

	


	

	@Test
	public void testCrearPeliculaConAnioMenorA1930() {
		PeliculaEntity peliculaTest = factory.manufacturePojo(PeliculaEntity.class);

		peliculaTest.setTitulo("Inception");
		peliculaTest.setAnioLanzamiento(1900);

		IllegalOperationException excepcion = assertThrows(IllegalOperationException.class, () -> {
			peliculaService.crearPelicula(peliculaTest);
		});

		assertTrue(excepcion.getMessage().contains("1930"), 
			"El mensaje debe mencionar el año 1930");

		long cantidad = peliculaRepository.count();
		assertEquals(0, cantidad, "La película NO debe guardarse en la BD");
	}

	

}
