package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Direccion;
import es.uc3m.tiw.model.ROL;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.daos.IPersona;
import es.uc3m.tiw.model.daos.PersonaDao;

/**
 * Servlet implementation class PersistenceServlet
 */
@WebServlet("/registrarse")
public class PersistenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="demo-model")
	EntityManager em;
	@Resource
	UserTransaction ut;
	IPersona dao;
	
	@Override
	public void init() throws ServletException {
	
		dao = new PersonaDao(em, ut);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersistenceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Usuario> listaUsuarios = null;
		try {
			listaUsuarios = dao.findAll();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		for (Usuario usuario : listaUsuarios) {
			out.println(usuario);
			out.println("<br/>");
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setNombre(request.getParameter("nombre"));
		usuario.setApellidos(request.getParameter("apellidos"));
		usuario.setNick(request.getParameter("nick"));
		usuario.setPassword(request.getParameter("password"));
		String rol = request.getParameter("rol");
		
		switch (rol) {
		case "ESTUDIANTE":
			usuario.setRol(ROL.ESTUDIANTE);
			break;
		case "PROFESOR":
			usuario.setRol(ROL.PROFESOR);
			break;
		case "ADMIN":
			usuario.setRol(ROL.ADMIN);
			
			break;
			

		}
		
		Direccion direccion = new Direccion();
		direccion.setCalle(request.getParameter("calle"));
		direccion.setCp(Integer.parseInt(request.getParameter("cp")));
		direccion.setNumero(Integer.parseInt(request.getParameter("numero")));
		direccion.setPoblacion(request.getParameter("poblacion"));
		//ejemplo de añadir una direccion
		usuario.setDireccion(direccion);
		//ejemplo añadir varias direcciones.
		List<Direccion> direcciones = new ArrayList<Direccion>();
		direcciones.add(direccion);
		usuario.setDirecciones(direcciones);
		
		try {
			dao.createUsuario(usuario);
			System.out.println("usuario creado");
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | IllegalStateException
				| SecurityException | SQLException | NotSupportedException
				| SystemException | HeuristicMixedException
				| HeuristicRollbackException | RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	response.sendRedirect("index.jsp");
	
	}

}
