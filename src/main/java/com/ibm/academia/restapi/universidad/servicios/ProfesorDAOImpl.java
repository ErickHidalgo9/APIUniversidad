package com.ibm.academia.restapi.universidad.servicios;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;
import com.ibm.academia.restapi.universidad.repositorios.CarreraRepository;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;
import com.ibm.academia.restapi.universidad.repositorios.ProfesorRepository;

@Service
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO {
	
	@Autowired
	private CarreraDAO carreraDao;
	
	@Autowired
	private CarreraRepository carreraRepository;

	@Autowired
	public ProfesorDAOImpl(@Qualifier("repositorioProfesor") PersonaRepository repository) {
		super(repository);

	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> findProfesoresByCarrera(String nombreCarrera) {

		return ((ProfesorRepository)repository).findProfesoresByCarrera(nombreCarrera);
	}
	
	@Override
	@Transactional
	public Persona actualizarProfesor(long profesorId, Persona profesor) {
		Optional<Persona> oProfesor = repository.findById(profesorId);
		
		if(!oProfesor.isPresent())
			throw new NotFoundException(String.format("El profesor con id %d no se encuentra", profesorId));
		
		Persona profesorActualizado = null;
		oProfesor.get().setNombre(profesor.getNombre());
		oProfesor.get().setApellido(profesor.getApellido());
		oProfesor.get().setDireccion(profesor.getDireccion());
		profesorActualizado = repository.save(oProfesor.get());
		
		return profesorActualizado;
		
	}

	@Override
	public Persona asociarCarreraProfesor(long carreraId, long profesorId) {
		
		Optional<Persona> oProfesor = repository.findById(profesorId);
		if(!oProfesor.isPresent())
			throw new NotFoundException(String.format("El profesor con id %d no existe", profesorId));
		
		
		Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);
		if(!oCarrera.isPresent())
			throw new NotFoundException(String.format("La carrera con id %d no existe", carreraId));
		
		
		Set<Carrera> carreras = new HashSet<>();
		carreras.add(oCarrera.get());
		
		//Set<Carrera> carreras = (Set<Carrera>) carreraDao.buscarTodos();
		
		((Profesor)oProfesor.get()).setCarreras(carreras);
		
		return repository.save(oProfesor.get());
		
	}
	
	

	
	/*
	 * 
	 * @Autowired //Es para inyectar
	//Qualifier Como  persona no es repositorio se le debe poner un bean es decir un nombre y sera el de repositorio Alumno
	public AlumnoDAOImpl(@Qualifier("repositorioAlumno") PersonaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional ( readOnly = true)
	public Iterable<Persona> buscarAlumnosPorNombreCarrera(String nombreCarrera) {

		return ((AlumnoRepository)repository).buscarAlumnosPorNombreCarrera(nombreCarrera);
	}
	 * */
	
	
	
}
