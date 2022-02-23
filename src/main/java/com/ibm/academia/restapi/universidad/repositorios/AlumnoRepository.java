package com.ibm.academia.restapi.universidad.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

@Repository("repositorioAlumno") //Se le pone un nombre porque varios heredarán de persona
public interface AlumnoRepository extends PersonaRepository{
	
	//Se copia a AlumnoDAO la parte de Iterable no la de query
	
	//Ya no se busca en la tabla Persona sino en alumnos
	//Alumno tiene una relacion con carrera por el nombre de la carrera por eso es a.carrera
	//Trae todas las carreras que coincidan con el nombre que se mandó
	//buscarAlumnosPorNombreCarrera es mas especifico que solo buscarAlumnosPorNombre
	
	
	//@Query("select a from Alumno a where a.carrera.nombre = ?1")
	
	//Join de alumno y carrera, se mandan a traer el nombre de las carreras por alumno 
	@Query("select a from Alumno a join fetch a.carrera c where c.nombre = ?1")
	public Iterable<Persona> buscarAlumnosPorNombreCarrera(String nombreCarrera);

}
