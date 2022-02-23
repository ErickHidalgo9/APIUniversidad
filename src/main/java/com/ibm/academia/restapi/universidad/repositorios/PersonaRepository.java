package com.ibm.academia.restapi.universidad.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;


//Como es Abstracta la clase Persona no es un repositorio
//cuando se crean objetos se haran por medio de la herencia
//Los repositorios son las que heredan
@NoRepositoryBean
public interface PersonaRepository extends CrudRepository<Persona, Long>{

	//ESTO YA ES CON JPQUERY JPQL
	
//Nombre se debe llamar exactamente como esta en Persona
// p.nombre es igual al primer parametro y p.apellido es igual al segundo parametro
@Query ("select p from Persona p where p.nombre = ?1 and p.apellido = ?2")
public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);

@Query ("select p from Persona p where p.dni = ?1")
public Optional<Persona> buscarPorDni(String dni);

//Se refiere a que buscara una lista de personas por el apellido
//Like quiere decir traeme apellidos que coincidan con cierto apellido
//Los porcentajes significan cuando son los 2 quiere decir que no importa donde esté lo que le voy a pasar
//Si se le pone %a es todo lo que empiece con a
//Si se le pone a% es todo lo que termine con a
//Si tiene %a% todo lo que tenga a
@Query("select p from Persona p where p.apellido like %?1%")
public Iterable<Persona> buscarPersonaPorApellido(String apellido);


}

