package es.uc3m.tiw.model;




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

	@Override
	public String toString() {
		
		return super.toString()+" Usuario [" + (rol != null ? "rol=" + rol : "") + "]";
	}

}