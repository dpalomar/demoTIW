package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Mensaje;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.daos.PersonaDao;
import es.uc3m.tiw.web.jms.EscribirEnQueue;

/**
 * Servlet implementation class MensajesServlet
 */
@WebServlet("/mensajes")
public class MensajesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="demo-model")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private PersonaDao dao;
	@Inject
	private EscribirEnQueue colaMensajes;
       

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		dao = new PersonaDao(em, ut);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mensaje = request.getParameter("mensaje");
		Usuario from = dao.findById(Integer.valueOf(request.getParameter("from")));
		Usuario to = dao.findById(Integer.valueOf(request.getParameter("to")));
		Map<Usuario, Usuario> fromTo = new HashMap<Usuario, Usuario>();
		HashMap<Map, Mensaje> conversacion = new HashMap<Map, Mensaje>();
		
		fromTo.put(from, to);
		conversacion.put(fromTo, new Mensaje(mensaje));
		colaMensajes.enviar(conversacion);
		
		this.getServletConfig().getServletContext().getRequestDispatcher("/login").forward(request, response);
		
		
		
	
	}

}
