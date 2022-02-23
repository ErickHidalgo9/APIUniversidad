package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

@DataJpaTest
public class EmpleadoRepositoryTest {
	
	@Autowired
	@Qualifier("repositorioEmpleado")
	private PersonaRepository empleadoRepository;
	
	@Test
	@DisplayName("Test: Buscar empleado por el tipo de empleado")
	void findEmpleadoByTipoEmpleado() {
		
		//Given
		Iterable<Persona> personas = empleadoRepository.saveAll(
				Arrays.asList(
						DatosDummy.empleado01(),
						DatosDummy.empleado02()
						)
				); 
		
		//When
		List<Persona> expected = (List<Persona>) ((EmpleadoRepository)empleadoRepository).findEmpleadoByTipoEmpleado(TipoEmpleado.MANTENIMIENTO);
		
		//Then
		assertThat(expected.size()==1).isTrue();
		
	}

}

