package es.uc3m.tiw.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Mensaje implements Serializable{

	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	private String mensaje;
	public Mensaje() {
		// TODO Auto-generated constructor stub
	}
	public Mensaje(String mensaje2) {
		this.mensaje = mensaje;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
