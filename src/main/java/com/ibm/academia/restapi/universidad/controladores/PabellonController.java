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
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.servicios.PabellonDAO;

@RestController
@RequestMapping("/restapi")
public class PabellonController {
	
	@Autowired
	@Qualifier
	private PabellonDAO pabellonDao;
	
	@PostMapping("/pabellon")
	public ResponseEntity<?> crearPabellon(@RequestBody Pabellon pabellon)
	{	
		Pabellon pabellonGuardado = pabellonDao.guardar(pabellon);
		return new ResponseEntity<Pabellon>(pabellonGuardado, HttpStatus.CREATED);
	}
	
	
	
	 @GetMapping("/pabellones/lista")
	public ResponseEntity<?> obtenerTodos()
	{
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.buscarTodos();
		
		if(pabellones.isEmpty())
			throw new NotFoundException("No existen pabellones");
		
		return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
	}
	 
	
	
	@GetMapping("/pabellon/{pabellonId}")
    public ResponseEntity<?> obtenerPabellonPorId(@PathVariable Long pabellonId)
    {
        Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
        
        if(!oPabellon.isPresent()) 
            throw new NotFoundException(String.format("Pabellon con id %d no existe", pabellonId));
        
        return new ResponseEntity<Pabellon>(oPabellon.get(), HttpStatus.OK);
    }
	 
	
	
	 @DeleteMapping("/pabellon/eliminar/pabellonId/{pabellonId}")
	public ResponseEntity<?> eliminarPabellon(@PathVariable Long pabellonId)
	{
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("El pabellon con ID %d no existe", pabellonId));
		
		pabellonDao.eliminarPorId(oPabellon.get().getId()); 
		return new ResponseEntity<String>("Pabellon ID: " + pabellonId + " se elimino satisfactoriamente",  HttpStatus.NO_CONTENT);
	}
	 
	
	 @PutMapping("/pabellon/actualizar/pabellonId/{pabellonId}")
	public ResponseEntity<?> actualizarPabellon(@PathVariable Long pabellonId, @RequestBody Pabellon pabellon)
	{
		Pabellon pabellonActualizado = ((PabellonDAO)pabellonDao).actualizar(pabellonId, pabellon);
		return new ResponseEntity<Pabellon>(pabellonActualizado, HttpStatus.OK);
	}
	 

}
