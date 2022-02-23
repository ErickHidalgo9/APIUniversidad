package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

@DataJpaTest
public class AulaRepositoryTest {
	
	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private PabellonRepository pabellonRepository;
	
	@Test
	@DisplayName("Test: Buscar Aula por el tipo de pizarron")
	void findByTipoPizarron() {
		
		//Given
		Iterable<Aula> aulas = aulaRepository.saveAll(
				Arrays.asList(
						DatosDummy.aula01(),
						DatosDummy.aula02()
						)
				);
		
		//When 
		List<Aula> expected = (List<Aula>) aulaRepository.findByTipoPizarron(TipoPizarron.PIZARRON_BLANCO);
		
		//Then
		assertThat(expected.size()==1).isTrue();
		
	}
	
	@Test
	@DisplayName("Test: Buscar Aula por su numero")
	void findByNumeroAula() {
		
		//Given
		Iterable<Aula> aulas = aulaRepository.saveAll(
				Arrays.asList(
					DatosDummy.aula01(),
					DatosDummy.aula02()
						)
		    	);
		
		//When
		Integer numeroAula = 2;
		List<Aula> expected = (List<Aula>) aulaRepository.findByNumeroAula(numeroAula);
		
		//Then
		assertThat(expected.size()==1).isTrue();
		
	}
	
	@Test
	@DisplayName("Test: Buscar Aula por nombre de Pabellon")
	void buscarAulaPorNombrePabellon() {
		//Given
		Iterable<Aula> aulas = aulaRepository.saveAll(
				Arrays.asList(
						DatosDummy.aula01(),
						DatosDummy.aula02()
						)
				);
		
		Pabellon pabellon = pabellonRepository.save(DatosDummy.pabellon01());
		aulas.forEach(pabellon1 -> pabellon1.setPabellon(pabellon));
		aulaRepository.saveAll(aulas);
		
		//When
		String nombrePabellon="pabellon1";
		List<Aula> expected = (List<Aula>) aulaRepository.findByPabellonNombre(nombrePabellon);
		
		//Then
		assertThat(expected.size()==2).isTrue();
		
	}

}

