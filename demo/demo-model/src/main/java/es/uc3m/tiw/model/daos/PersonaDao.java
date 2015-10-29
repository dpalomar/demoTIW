package es.uc3m.tiw.model.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.uc3m.tiw.model.Usuario;

public class PersonaDao extends ConnectionDAO implements IPersona {



	private static final String SELECT_FROM_USUARIO_WHERE = "SELECT * FROM usuario WHERE ";
	private static final String SELECT_ALL_FROM_USUARIO = "SELECT * FROM usuario";
	private static Connection con = null;
	private static IPersona dao;
	static{
		try {
			dao = new PersonaDao();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private  PersonaDao() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	
		con = getConnection();
	
	}
	public static IPersona getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		if (dao == null) {
			dao = new PersonaDao();
		}
		return dao;
	}
	
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.model.daos.IPersona#createUsuario(es.uc3m.tiw.model.Usuario)
	 */
	@Override
	public Usuario createUsuario(Usuario usuarioNuevo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Statement st = null;
		
			st = con.createStatement();
			st.executeUpdate("INSERT INTO usuario (`nombre`, `apellidos`) VALUES ('"+usuarioNuevo.getNombre()+"', '"+usuarioNuevo.getApellidos()+"');");
			ResultSet rs = st.executeQuery(SELECT_FROM_USUARIO_WHERE+" 'nombre'="+usuarioNuevo.getNombre());
			Usuario usuario = null;
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				int idUsuario = rs.getInt("idusuario");
				usuario = new Usuario();
				usuario.setNombre(nombre);
				usuario.setApellidos(apellidos);
				usuario.setId(new Long(idUsuario));
			
				
			}
			closeAll(rs, st, con);
			

		return usuario;
		
	}

	/* (non-Javadoc)
	 * @see es.uc3m.tiw.model.daos.IPersona#findAll()
	 */
	@Override
	public List<Usuario> findAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		

		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(SELECT_ALL_FROM_USUARIO);
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		while (rs.next()) {
			String nombre = rs.getString("nombre");
			String apellidos = rs.getString("apellidos");
			Usuario usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellidos(apellidos);
			listaUsuarios.add(usuario);
			
		}
		closeAll(rs, st, con);
		
		return listaUsuarios;
	}
	@Override
	public Usuario findById(int id) throws SQLException{
		Statement st = null;
		
		st = con.createStatement();
		
		ResultSet rs = st.executeQuery(SELECT_FROM_USUARIO_WHERE+" 'idusuario'="+id);
		Usuario usuario = null;
		while (rs.next()) {
			String nombre = rs.getString("nombre");
			String apellidos = rs.getString("apellidos");
			int idUsuario = rs.getInt("idusuario");
			usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellidos(apellidos);
			usuario.setId(new Long(idUsuario));
		
			
		}
		closeAll(rs, st, con);
		

	return usuario;
	}
}
