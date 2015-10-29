package es.uc3m.tiw.model.daos;

import java.sql.SQLException;
import java.util.List;

import es.uc3m.tiw.model.Usuario;

public class EstudianteDAO extends ConnectionDAO implements IPersona {

	public EstudianteDAO() {
	
		//TODO: cargar la conexion desde el padre
		//TODO: conexion tipo unico singleton
		
	}
	
	@Override
	public Usuario createUsuario(Usuario usuarioNuevo)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findAll() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario findById(int id) throws SQLException {
		return null;
	}

}
