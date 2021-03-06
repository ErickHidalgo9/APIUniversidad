package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;

public interface AulaDAO extends GenericoDAO<Aula>{
	
	public Iterable<Aula> findByTipoPizarron(TipoPizarron tipoPizarron);
	
	public Iterable<Aula> findByPabellonNombre(String nombrePabellon);
	
	public Iterable<Aula> findByNumeroAula(Integer numero);
	
	public Aula actualizar(Long aulaId, Aula aula); 

}