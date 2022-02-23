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
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

@DataJpaTest
public class PabellonRepositoryTest {
	
	@Autowired
	private PabellonRepository pabellonRepository;
	
	@Test
	@DisplayName("Test: Buscar Pabellon por nombre")
	void buscarPabellonPorNombre() {
		
		//Given 
		Iterable<Pabellon> pabellones = pabellonRepository.saveAll(
				Arrays.asList(
						DatosDummy.pabellon01(),
						DatosDummy.pabellon02()
						)
				
				);
		
		//When
		String nombrePabellon="pabellon1";
		List<Pabellon> expected = (List<Pabellon>) pabellonRepository.findByNombreContainsIgnoreCase(nombrePabellon);
		
		//Then
		assertThat(expected.size()==1).isTrue();
	}

}

