package com.ibm.academia.restapi.universidad.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ibm.academia.restapi.universidad.datos.DatosDummy.*;

import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.repositorios.AulaRepository;

public class AulaDAOImplTest {

	AulaDAO aulaDao;
	
	AulaRepository aulaRepository;
	
	
	@BeforeEach
	void setUp() {
		aulaRepository = mock(AulaRepository.class);
		
		aulaDao = new AulaDAOImpl(aulaRepository);
	}
	
	@Test
	@DisplayName("Test: Buscar aula por su numero")
	void findByNumeroAula() {
		
		//Given 
		Integer numeroAula=1;
		when(aulaRepository.findByNumeroAula(numeroAula)).thenReturn(Arrays.asList(aula01()));
		
		//When
		List<Aula> expected = (List<Aula>) aulaDao.findByNumeroAula(numeroAula);
		
		//Then
		assertThat(expected.get(0)).isEqualTo(aula01());
		
		verify(aulaRepository).findByNumeroAula(numeroAula);
		
	}
	
}
