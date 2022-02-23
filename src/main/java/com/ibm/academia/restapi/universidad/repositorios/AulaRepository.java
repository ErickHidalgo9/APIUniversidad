package com.ibm.academia.restapi.universidad.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;

@Repository
public interface AulaRepository extends CrudRepository<Aula, Long> {
	
	public Iterable<Aula> findByTipoPizarron(TipoPizarron tipoPizarron);
	
	public Iterable<Aula> findByPabellonNombre(String nombrePabellon);
	
	public Iterable<Aula> findByNumeroAula(Integer numero);

}
