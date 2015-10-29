package es.uc3m.tiw.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * Entity implementation class for Entity: Persona
 *
 */

public class Persona implements Serializable {

	
	private static final long serialVersionUID = 1L;
	

	private Long id;
	private String nombre;
	private String apellidos;
	

	

	public Persona() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	@Override
	public String toString() {
		return "Persona [" + (id != null ? "id=" + id + ", " : "")
				+ (nombre != null ? "nombre=" + nombre + ", " : "")
				+ (apellidos != null ? "apellidos=" + apellidos : "") + "]";
	}





   
}
