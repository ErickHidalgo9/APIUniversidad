package com.ibm.academia.restapi.universidad.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.Query;

import static com.ibm.academia.restapi.universidad.datos.DatosDummy.*;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.repositorios.CarreraRepository;


public class CarreraDAOImplTest {
	
	CarreraDAO carreraDao;
	CarreraRepository carreraRepository;
	
	@BeforeEach
	void setUp() {
		//Mock va a simular repositorio carrera
		carreraRepository = mock(CarreraRepository.class);
		
		//Aqui necesitamos ir a donde estan los metodos de abajo por eso accedemos a CarreraDAOImpl
		carreraDao = new CarreraDAOImpl(carreraRepository);
	}
	
	@Test
	@DisplayName("Test: Buscar carreras por nombre")
	void findCarrerasByNombreContains(){
		//Given
		String nombreCarrera = "Ingenieria";
		when(carreraRepository.findCarrerasByNombreContains(nombreCarrera)).thenReturn(Arrays.asList(carrera01(),carrera03()));
		
		//When 
		List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByNombreContains(nombreCarrera);
		
		//Then
		assertThat(expected.get(0)).isEqualTo(carrera01());
		assertThat(expected.get(1)).isEqualTo(carrera03());
		
		//Validamos que nuestro repositorio este llamando a findCarrerasByNombreContains
		verify(carreraRepository).findCarrerasByNombreContains(nombreCarrera);

		//Si le pasamos otro nos manda error..
		//verify(carreraRepository).findCarrerasByNombreContainsIgnoreCase(nombreCarrera);
		
	}
	
	@Test
	@DisplayName("Test: Buscar carreras por nombre NO case sensitive")
	void findCarrerasByNombreContainsIgnoreCase(){
		
		//Given
		String nombre = "Ingenieria";
		when(carreraRepository.findCarrerasByNombreContainsIgnoreCase(nombre)).thenReturn(Arrays.asList(carrera01(),carrera03()));
		
		//When 
		List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByNombreContainsIgnoreCase(nombre);
		
		//Then
		assertThat(expected.get(0)).isEqualTo(carrera01());
		assertThat(expected.get(1)).isEqualTo(carrera03());
		
		verify(carreraRepository).findCarrerasByNombreContainsIgnoreCase(nombre);
	}
	
	@Test
	@DisplayName("Test: Buscar carreras mayores a N años")
	void findCarrerasByCantidadAniosAfter(){
		
		//Given
			Integer cantidad= 4;
			when(carreraRepository.findCarrerasByCantidadAniosAfter(cantidad)).thenReturn(Arrays.asList(carrera01(),carrera03()));
				
			//When 
			List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByCantidadAniosAfter(cantidad);
				
			//Then
			assertThat(expected.get(0)).isEqualTo(carrera01());
			assertThat(expected.get(1)).isEqualTo(carrera03());
				
			verify(carreraRepository).findCarrerasByCantidadAniosAfter(cantidad);
		
				
	}
	
	@Test
	@Disabled
	@Query ("select c from Carrera c join fetch c.profesores pr where pr.nombre like %?1% and pr.apellido like %?2%")
	void buscarCarrerasPorProfesorNombreYApellido(){
		
	}

}

