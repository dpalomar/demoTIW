package es.uc3m.tiw.model.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import es.uc3m.tiw.model.Usuario;

public interface IPersona {

	public abstract Usuario createUsuario(Usuario usuarioNuevo)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException;

	public abstract List<Usuario> findAll() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException;

	public abstract Usuario findById(int id) throws SQLException;

}