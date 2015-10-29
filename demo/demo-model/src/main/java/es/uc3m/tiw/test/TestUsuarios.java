package es.uc3m.tiw.test;

import java.sql.SQLException;
import java.util.List;

import es.uc3m.tiw.model.ROL;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.daos.IPersona;
import es.uc3m.tiw.model.daos.PersonaDao;

public class TestUsuarios {

	public static void main(String[] args) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Ana");
		usuario.setApellidos("Perez");
		usuario.setRol(ROL.ESTUDIANTE);
		
		try {
			IPersona dao = PersonaDao.getInstance();
			dao.createUsuario(usuario);
			List<Usuario> lista = 	dao.findAll();
			for (Usuario usuario2 : lista) {
				System.out.println(usuario2);
				
			}
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
