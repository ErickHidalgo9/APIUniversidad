package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;


public interface CarreraDAO extends GenericoDAO<Carrera> {

	
	public Iterable<Carrera> findCarrerasByNombreContains(String nombre);


	public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre); // Esto es JPQL con metodos

	
	public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);
	
	
	
	public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
	
	public Carrera actualizar(Long carreraId, Carrera carrera);

}
