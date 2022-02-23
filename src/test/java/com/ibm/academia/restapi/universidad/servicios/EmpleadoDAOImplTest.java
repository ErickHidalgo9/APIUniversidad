package com.ibm.academia.restapi.universidad.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.ibm.academia.restapi.universidad.datos.DatosDummy.*;
import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.EmpleadoRepository;

@SpringBootTest
public class EmpleadoDAOImplTest {
	
	EmpleadoDAO empleadoDao;
	
	EmpleadoRepository empleadoRepository;
	
	void SetUp() {
		empleadoRepository = mock(EmpleadoRepository.class);
		
		empleadoDao = new EmpleadoDAOImpl(empleadoRepository);
	}
	
	@Test
	@Disabled
	@DisplayName("Test: Buscar empleado por el tipo de empleado")
	void findEmpleadoByTipoEmpleado() {
		
		//Given 
		TipoEmpleado tipo1 = TipoEmpleado.ADMINISTRATIVO;
		when(empleadoRepository.findEmpleadoByTipoEmpleado(tipo1)).thenReturn(Arrays.asList(empleado01()));
		
		//When
		List<Persona> expected = (List<Persona>) empleadoDao.findEmpleadoByTipoEmpleado(tipo1);
		
		//Then
		assertThat(expected.get(0)).isEqualTo(empleado01());
		
		verify(empleadoRepository.findEmpleadoByTipoEmpleado(tipo1)); 
		
	}
	
	
	/*
	@MockBean
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private EmpleadoDAO empleadoDao;
	
	@Test
	void findEmpleadobyTipoEmpleado() {
		//When
		empleadoDao.findEmpleadoByTipoEmpleado(TipoEmpleado.valueOf(anyString()));
		
		//then
		
		verify(empleadoRepository.findEmpleadoByTipoEmpleado(TipoEmpleado.valueOf(anyString())));
		
	}*/
	
	

}

