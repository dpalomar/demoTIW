package es.uc3m.tiw.ejb;

import javax.ejb.Local;

@Local
public interface SaludosBeanLocal {

	public abstract String saludar(String nombre);

}
