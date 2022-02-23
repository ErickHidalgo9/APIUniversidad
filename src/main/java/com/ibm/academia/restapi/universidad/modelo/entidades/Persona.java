package com.ibm.academia.restapi.universidad.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="personas", schema="universidad")
//@Table(name="personas")
@Inheritance (strategy = InheritanceType.JOINED)

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "tipo"
)
@JsonSubTypes(
		{
			@JsonSubTypes.Type(value = Alumno.class, name = "alumno"),
			@JsonSubTypes.Type(value = Profesor.class, name = "profesor"),
			@JsonSubTypes.Type(value = Empleado.class, name = "empleado")
})

public abstract class Persona implements Serializable{
	
	
	
	//Todos los id deben ser Long
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty
	@Column(name="nombre", nullable = false, length = 60)
	private String nombre;
	
	@NotNull
	@NotEmpty
	@Column(name="apellido", nullable = false, length = 60)
	private String apellido;
	
	@NotNull
	@NotEmpty
	@Column(name="dni", nullable = false, unique = true, length = 10)
	private String dni;
	
	@NotNull
	@NotEmpty
	@Column(name = "usuario_creacion", nullable = false)
	private String usuarioCreacion;
	
	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion;
	
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;
	
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "codigoPostal", column = @Column (name="codigo_postal")),
		@AttributeOverride(name = "departamento", column = @Column (name="departamento"))
	})
	
	private Direccion direccion;
	
	
	public Persona(Long id, String nombre, String apellido, String dni, String usuarioCreacion, Direccion direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.direccion = direccion;
		this.usuarioCreacion = usuarioCreacion;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Persona [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido=");
		builder.append(apellido);
		builder.append(", dni=");
		builder.append(dni);
		builder.append(", usuarioCreacion=");
		builder.append(usuarioCreacion);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", fechaModificacion=");
		builder.append(fechaModificacion);
		builder.append(", direccion=");
		builder.append(direccion);
		builder.append("]");
		return builder.toString();
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(dni, id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(id, other.id);
	}
	
	@PrePersist
	private void antesPersistir() {
		this.fechaCreacion = new Date();
	}
	
	@PreUpdate
	private void antesActualizar() {
		this.fechaModificacion = new Date();
	}
	
	private static final long serialVersionUID = -7120281028155760481L;
	

}
