package com.ibm.academia.restapi.universidad.datos;

import java.math.BigDecimal;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;

public class DatosDummy {
	
	public static Carrera carrera01() {
		
		return new Carrera(null,"Ingenieria en Sistemas", 50, 5, "ehidalgo");
	}
	
	public static Carrera carrera02() {
			
			return new Carrera(null,"Licenciatura en Sistemas", 45, 4, "ehidalgo");
		}

	public static Carrera carrera03() {
		
		return new Carrera(null,"Ingenieria en Industrial", 60, 5, "ehidalgo");
	}
	
	public static Persona empleado01() {
		return new Empleado(null, "Lautaro", "Lopez", "12345678", "ehidalgo", new Direccion(), new BigDecimal("46750"), TipoEmpleado.ADMINISTRATIVO);
	}
	
	public static Persona empleado02() {
		return new Empleado(null, "Leandro", "Lopez", "12345668", "ehidalgo", new Direccion(), new BigDecimal("46760"), TipoEmpleado.MANTENIMIENTO);
	}
	
	public static Persona profesor01() {
		return new Profesor(null, "Octavio", "Perez", "12345672", "ehidalgo", new Direccion(), new BigDecimal("67892"));
	}
	
	public static Persona profesor02() {
		return new Profesor(null, "Bruno", "Mendez", "12345123", "ehidalgo", new Direccion(), new BigDecimal("18792"));
	}
	
	public static Persona alumno01() {
		return new Alumno(null, "Jhon", "Benitez", "112344", "ehidalgo", new Direccion());
	}
	
	public static Persona alumno02() {
		return new Alumno(null, "Andres", "Benitez", "117732", "ehidalgo", new Direccion());
	}
	
	public static Persona alumno03() {
		return new Alumno(null, "Joaquin", "Leon", "0123432", "ehidalgo", new Direccion());
	}
	
	public static Aula aula01() {
		return new Aula(null, 1, "60x60", 35, TipoPizarron.PIZARRON_BLANCO, "ehidalgo");
	}
	
	public static Aula aula02() {
		return new Aula(null, 2, "60x80", 28, TipoPizarron.PIZARRON_TIZA, "ehidalgo");
	}
	
	public static Pabellon pabellon01() {
		return new Pabellon(null, 100.5, "pabellon1", new Direccion(), "ehidalgo");
	}
	
	public static Pabellon pabellon02() {
		return new Pabellon(null, 150.8, "pabellon2", new Direccion(), "ehidalgo");
	}

}
