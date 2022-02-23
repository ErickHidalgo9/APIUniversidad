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

import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.repositorios.PabellonRepository;

public class PabellonDAOImplTest {
	
	PabellonDAO pabellonDao;
	PabellonRepository pabellonRepository;
	
	@BeforeEach
	void setUp() {
		pabellonRepository = mock(PabellonRepository.class);
		
		pabellonDao = new PabellonDAOImpl(pabellonRepository);
	}
	
	@Test
	@DisplayName("Buscar Pabellon por nombre")
	void buscarPabellonPorNombre() {
		//Given
		String nombrePabellon="pabellon1";
		when(pabellonRepository.findByNombreContainsIgnoreCase(nombrePabellon)).thenReturn(Arrays.asList(pabellon01()));
		
		//When
		List<Pabellon> expected = (List<Pabellon>) pabellonDao.findByNombreContainsIgnoreCase(nombrePabellon);
		
		//Then
		assertThat(expected.get(0)).isEqualTo(pabellon01());
		
		verify(pabellonRepository).findByNombreContainsIgnoreCase(nombrePabellon);
		
	}

}
