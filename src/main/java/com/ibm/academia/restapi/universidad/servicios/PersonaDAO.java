package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public interface PersonaDAO extends GenericoDAO<Persona>{
	
	//ESTO YA ES CON JPQUERY JPQL
public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);

public Optional<Persona> buscarPorDni(String dni);

//Se refiere a que buscara una lista de personas por el apellido
public Iterable<Persona> buscarPersonaPorApellido(String apellido);

}
