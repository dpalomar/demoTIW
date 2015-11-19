package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.tiw.ejb.SaludosBeanLocal;

/**
 * Este servlet muestra como inyectar un EJB SessionBean Stateless desde el proyecto -ejb e invocarlo
 * desde el proyecto web, todo dentro del mismo EAR
 * La forma de invocarlo es pasar un parametro "nombre"
 * 
 * <strong>http://localhost:8080/demo-web/bienvenida?nombre=UnNombre</strong>
 * 
 */
@WebServlet(description = "Ejemplo para usar inyeccion de EJBs", urlPatterns = { "/bienvenida" })
public class SaludosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(mappedName="servicioSaludos")
	private SaludosBeanLocal servicio;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaludosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String mensaje = servicio.saludar(nombre);
		PrintWriter out = response.getWriter();
		out.println(mensaje);
		out.close();
	}

}
