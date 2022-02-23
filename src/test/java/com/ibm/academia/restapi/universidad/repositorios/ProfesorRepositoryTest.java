package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;

@DataJpaTest
public class ProfesorRepositoryTest {
	
	@Autowired
	@Qualifier("repositorioProfesor")
	private PersonaRepository profesorRepository;
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Test
	@DisplayName("Test: Buscar profesores por nombre de la carrera")
	void buscarProfesoresPorNombreCarrera() {
		
		//Given
		Iterable<Persona> personas = profesorRepository.saveAll(
				Arrays.asList(
						DatosDummy.profesor01(),
						DatosDummy.profesor02()
						)
				);
		
		Set<Carrera> carreras = new HashSet<>();
		carreras.add(DatosDummy.carrera01());
		carreras.add(DatosDummy.carrera02());
		
		personas.forEach(profesor -> ((Profesor)profesor).setCarreras(carreras));
		profesorRepository.saveAll(personas);
	
		//When
		String nombreCarrera = "Ingenieria en Sistemas";
		List<Persona> expected = (List<Persona>) ((ProfesorRepository)profesorRepository).findProfesoresByCarrera(nombreCarrera);
		
		//Then
		assertThat(expected.size() == 2).isTrue();
		
	}

}

