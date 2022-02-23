package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.AulaRepository;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO{

	@Autowired
	public AulaDAOImpl(AulaRepository repository) {
		super(repository);
	
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula> findByTipoPizarron(TipoPizarron tipoPizarron) {
		return repository.findByTipoPizarron(tipoPizarron);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula> findByPabellonNombre(String nombrePabellon) {
		return repository.findByPabellonNombre(nombrePabellon);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula> findByNumeroAula(Integer numero) {
		return repository.findByNumeroAula(numero);
	}

	
	
	@Override
	@Transactional
	public Aula actualizar(Long aulaId, Aula aula) {

		Optional<Aula> oAula = repository.findById(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("El aula con id %d no existe", aulaId));
		
		Aula aulaActualizada = null;
		oAula.get().setNumeroAula(aula.getNumeroAula());
		oAula.get().setCantidadPupitres(aula.getCantidadPupitres());
		oAula.get().setTipoPizarron(aula.getTipoPizarron());
		aulaActualizada = repository.save(oAula.get());
		
		return aulaActualizada;
	}
	
	

}
