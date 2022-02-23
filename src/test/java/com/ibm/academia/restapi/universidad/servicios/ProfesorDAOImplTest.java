package com.ibm.academia.restapi.universidad.servicios;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.universidad.repositorios.ProfesorRepository;

@SpringBootTest
public class ProfesorDAOImplTest {
	
	@MockBean
	private ProfesorRepository profesorRepository;
	
	@Autowired
	ProfesorDAO profesorDao;
	
	@Test
	void buscarProfesoresPorNombreCarrera() {
		
		//When
		profesorDao.findProfesoresByCarrera(anyString());
		
		//Then
		verify(profesorRepository).findProfesoresByCarrera(anyString());
	}

}
