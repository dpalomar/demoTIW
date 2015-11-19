package es.uc3m.tiw.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class SaludosBean
 */
@Stateless(mappedName = "servicioSaludos")
@LocalBean
public class SaludosBean implements SaludosBeanLocal {

    /**
     * Default constructor. 
     */
    public SaludosBean() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public String saludar(String nombre){
    	return "Hola "+nombre;
    }

}
