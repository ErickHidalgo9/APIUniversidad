package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon>{

	public Iterable<Pabellon> findByDireccionContaining(String localidad);
	
	public Iterable<Pabellon> findByNombreContainsIgnoreCase(String nombre);
	
	public Pabellon actualizar(Long aulaId, Pabellon pabellon); 
}

