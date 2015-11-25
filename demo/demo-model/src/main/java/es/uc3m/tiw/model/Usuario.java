package es.uc3m.tiw.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;



@Entity
public class Usuario extends Persona {

	private static final long serialVersionUID = 1L;



	private ROL rol;

	public ROL getRol() {
		return rol;
	}

	public void setRol(ROL rol) {
		this.rol = rol;
	}

	public Usuario() {
	}

	public Usuario(String nombre, String apellidos, String nick, String password) {
		super(nombre, apellidos, nick, password);
	}
	public Usuario(String nombre, String apellidos, String nick, String password,ROL rol) {
		super(nombre, apellidos, nick, password);
		this.rol = rol;
	}

	@Override
	public String toString() {
		
		return super.toString()+" Usuario [" + (rol != null ? "rol=" + rol : "") + "]";
	}



}