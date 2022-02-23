package com.ibm.academia.restapi.universidad.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;

@Repository
public interface CarreraRepository extends CrudRepository<Carrera, Long>{
	
	//Buscar las carreras que contienen algun nombre
	//@Query ("select c from Carrera c where c.nombre like %?1%") esto es igual al metodo de abajo que ya lo hace
	public Iterable<Carrera> findCarrerasByNombreContains(String nombre);
	
	//Ignorecase ignora mayusculas y minusculas
	//@Query ("select c from Carrera c where upper(c.nombre) like upper(%?1%")) esto es igual al metodo de abajo  <--esto es JPQL 
	public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);  //Esto es JPQL con metodos 
	
	
	//@Query ("select c from Carrera c where c.cantidadAnios > ?1) Significa que cantidad sea mayor al dato que envie, cantidadAnios debe ser escrito igual que la clase Carrera
	public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);
	
	//Aqui va el Query
	@Query ("select c from Carrera c join fetch c.profesores pr where pr.nombre like %?1% and pr.apellido like %?2%")
	public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);

}
