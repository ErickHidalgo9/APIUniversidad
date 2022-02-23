package com.ibm.academia.restapi.universidad.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.AlumnoDAO;
import com.ibm.academia.restapi.universidad.servicios.AulaDAO;

@RestController
@RequestMapping("/restapi")
public class AulaController {
	
	@Autowired
	@Qualifier("aulaDAOImpl")
	private AulaDAO aulaDao;
	
	/*
	 * @GetMapping("/carreras/lista") //Esto es para obtener, son metodos de consulta, para decorar el endpoint
	public List<Carrera> listarTodas(){
		
		List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();
		return carreras;
	}
	 *
	 * 
	 * @PostMapping("/alumno")
	public ResponseEntity<?> crearAlumno(@RequestBody Persona alumno)
	{	
		Persona alumnoGuardado = alumnoDao.guardar(alumno);
		return new ResponseEntity<Persona>(alumnoGuardado, HttpStatus.CREATED);
	}
	 * */
	
	@PostMapping("/aula")
	public ResponseEntity<?> crearAula(@RequestBody Aula aula){
		Aula aulaGuardada = aulaDao.guardar(aula);
		return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
	}
	
	/*
	 * @GetMapping("/alumnos/lista")
	public ResponseEntity<?> obtenerTodos()
	{
		List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();
		
		if(alumnos.isEmpty())
			throw new NotFoundException("No existen alumnos");
		
		return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
	}
	 * 
	 * */

	@GetMapping("/aulas/lista")
	public ResponseEntity<?> obtenerTodas(){
		List<Aula> aulas = (List<Aula>) aulaDao.buscarTodos();
		
		if(aulas.isEmpty())
			throw new NotFoundException("No hay aulas");
		
		return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
		
	}
	
	/*
	 * 
	 * @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Long alumnoId)
    {
        Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
        
        if(!oAlumno.isPresent()) 
            throw new NotFoundException(String.format("Alumno con id %d no existe", alumnoId));
        
        return new ResponseEntity<Persona>(oAlumno.get(), HttpStatus.OK);
    }
	 * 
	 * */
	
	@GetMapping("/aula/{aulaId}")
	public ResponseEntity<?> obtenerAulaPorId(@PathVariable Long aulaId){
		
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("Aula con id %d no existe", aulaId));
		
		return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);
	}
	
	/*
	 * @DeleteMapping("/alumno/eliminar/alumnoId/{alumnoId}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Long alumnoId)
	{
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		
		if(!oAlumno.isPresent())
			throw new NotFoundException(String.format("El alumno con ID %d no existe", alumnoId));
		
		alumnoDao.eliminarPorId(oAlumno.get().getId()); 
		return new ResponseEntity<String>("Alumno ID: " + alumnoId + " se elimino satisfactoriamente",  HttpStatus.NO_CONTENT);
	}
	 * 
	 * */
	
	@DeleteMapping("/aula/eliminar/aulaId/{aulaId}")
	public ResponseEntity<?> eliminarAula(@PathVariable Long aulaId){
		
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("Aula con id %d no existe", aulaId));
		
		aulaDao.eliminarPorId(oAula.get().getId());
		return new ResponseEntity<String>("Aula Id: " + aulaId + " se eliminó correctamente", HttpStatus.NO_CONTENT);
	}
	
	/*
	 * @PutMapping("/alumno/actualizar/alumnoId/{alumnoId}")
	public ResponseEntity<?> actualizarAlumno(@PathVariable Long alumnoId, @RequestBody Persona alumno)
	{
		Persona alumnoActualizado = ((AlumnoDAO)alumnoDao).actualizar(alumnoId, alumno);
		return new ResponseEntity<Persona>(alumnoActualizado, HttpStatus.OK);
	}
	 * 
	 * */
	
	@PutMapping("/aula/actualizar/aulaId/{aulaId}")
	public ResponseEntity<?> actualizarAula(@PathVariable Long aulaId, @RequestBody Aula aula){
		
		Aula aulaActualizada = aulaDao.actualizar(aulaId, aula);
		return new ResponseEntity<Aula>(aulaActualizada, HttpStatus.OK);
	}
}
