package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.ejb.SaludosBeanLocal;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.daos.PersonaDao;

/**
 * Servlet de ejemplo que muestra distintos aspectos dentro de los ambitos request y session. 
 * -Como usar el metodo init para inicializar datos
 * -Como recoger datos desde un formulario por post
 * -Como no permitir acceso por get redirigiendo a la pagina de login.jsp
 * -Uso del objeto RequestDispatcher y forward
 * -Introduccion de atributos en el objeto request
 * -Uso de objeto sesion para mantener al usuario autenticado
 * -Control de flujo y logica de negicio de un controlador.
 * 
 */
@WebServlet(value="/login",loadOnStartup=1)
public class LoginServlet extends HttpServlet {

	private static final String ENTRADA_JSP = "/listado.jsp";
	private static final String LOGIN_JSP = "/login.jsp";
	private static final long serialVersionUID = 1L;
	private es.uc3m.tiw.model.Usuario usuario;
	private List<Usuario> usuarios;
	//inyeccion de la persistencia
	@PersistenceContext(unitName="demo-model")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private PersonaDao dao;
	//inyeccion del EJB
	@EJB
	private SaludosBeanLocal servicioSaludos;
	@Override
	public void init() throws ServletException {
	
		dao = new PersonaDao(em, ut);
		
		
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String salir = request.getParameter("accion");
		if (salir != null && !salir.equals("")) {
			request.getSession().invalidate();
		}
		this.getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("usuario");
		String password = request.getParameter("password");
		String mensaje ="";
		String pagina = ENTRADA_JSP;
		HttpSession sesion = request.getSession(true);
		Usuario u = comprobarUsuario(user, password);
		if (sesion.getAttribute("acceso")!=null && sesion.getAttribute("acceso").equals("ok")){
			
			crearListaUsuarios(request, sesion, (Usuario)sesion.getAttribute("usuario"));
			
		}
		else if (u != null) {
			crearListaUsuarios(request, sesion, u);
		}
		else{
			pagina = LOGIN_JSP;
			mensaje = "Usuario o password incorrectos";
			request.setAttribute("mensaje", mensaje);
		}
		
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);
			
		
	}



	private void crearListaUsuarios(HttpServletRequest request,
			HttpSession sesion, Usuario u) {
		String mensaje;
		try {
		
			usuarios = dao.findAll();
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("usuarios", usuarios);
		sesion.setAttribute("usuario", u);
		sesion.setAttribute("acceso", "ok");
		mensaje = servicioSaludos.saludar(u.getNombre());
		request.setAttribute("mensaje", mensaje);
	}

	private Usuario  comprobarUsuario(String user, String password) {
		Usuario usuario = dao.findByNickAndPassword(user,password);
		return usuario;
	}

}
