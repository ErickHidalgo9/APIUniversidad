package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Query;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;


@DataJpaTest
public class CarreraRepositoryTest {
	
		@Autowired
		private CarreraRepository carreraRepository;
		
		@Autowired
		@Qualifier("repositorioProfesor")
		private PersonaRepository profesorRepository;
		
		@BeforeEach
		void setUp() {
			carreraRepository.save(DatosDummy.carrera01());
			carreraRepository.save(DatosDummy.carrera02());
			carreraRepository.save(DatosDummy.carrera03());
		}
		
		@AfterEach
		void tearDown() {
			carreraRepository.deleteAll();
		}
	
		@Test
		@DisplayName("Test: Buscar carreras por nombre")
		void findCarrerasByNombreContains(){
			//Given
			/*carreraRepository.save(DatosDummy.carrera01());
			carreraRepository.save(DatosDummy.carrera02());
			carreraRepository.save(DatosDummy.carrera03());*/
			
			//When
			List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByNombreContains("Industrial");
			
			//Then
			assertThat(expected.size() == 1).isTrue();
			
		}
		
		@Test
		@DisplayName("Test: Buscar carreras por nombre NO case sensitive")
		void findCarrerasByNombreContainsIgnoreCase(){
			
			//Given
			/*carreraRepository.save(DatosDummy.carrera01());
			carreraRepository.save(DatosDummy.carrera02());
			carreraRepository.save(DatosDummy.carrera03());*/
			
			//When
			List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByNombreContainsIgnoreCase("sistemas");
			
			//Then
			assertThat(expected.size() == 2).isTrue();
			
			
		}
		
		
		@Test
		@DisplayName("Test: Buscar carreras mayores a N años")
		void findCarrerasByCantidadAniosAfter(){
			
			//Given
			/*carreraRepository.save(DatosDummy.carrera01());
			carreraRepository.save(DatosDummy.carrera02());
			carreraRepository.save(DatosDummy.carrera03());*/
			
			//When
			List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByCantidadAniosAfter(4);
			
			//Then
			//Por lo menos 2 carreras de 4 años
			assertThat(expected.size() == 2).isTrue();
			
		}
		
		
		@Test
		@Disabled
		@DisplayName("Test: Buscar carreras por nombre y apellido del Profesor")
		void buscarCarrerasPorProfesorNombreYApellido(){
			//Given
			Iterable<Carrera> carreras = carreraRepository.saveAll(
					Arrays.asList(
							DatosDummy.carrera01(),
							DatosDummy.carrera02(),
							DatosDummy.carrera03()
							)
					);
			
			Set<Persona> profesores = new HashSet<>();
			profesores.add(DatosDummy.profesor01());
			profesores.add(DatosDummy.profesor02());
			
			//carreras.forEach(carrera -> ((Carrera)carrera).setProfesores(profesores));
			
			//When 
			String nombreProfesor = "Octavio";
			String apellidoProfesor = "Perez";
			
			List<Carrera> expected = (List<Carrera>) carreraRepository.buscarCarrerasPorProfesorNombreYApellido(nombreProfesor, apellidoProfesor);
			
			
			//Then
			assertThat(expected.size()==1).isTrue();
			
		}

}

