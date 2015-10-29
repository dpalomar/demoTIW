package es.uc3m.tiw.model.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDAO {

	static final String URL_MYSQL = "jdbc:mysql://localhost:3306/tiw";
	static final String PASSWORD = "admin";
	static final String USER = "root";
	static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

	public ConnectionDAO() {
		super();
	}

	protected void closeAll(ResultSet rs, Statement st, Connection con) throws SQLException {
		closeResultSet(rs);
		closeStatement(st);
		closeConnection(con);
	}

	protected void closeStatement(Statement st) throws SQLException {
		st.close();
	}

	protected void closeConnection(Connection con) throws SQLException {
		con.close();
	}

	protected Connection getConnection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
				Connection con;
				Class.forName(MYSQL_DRIVER).newInstance();
				con = DriverManager.getConnection(URL_MYSQL,USER,PASSWORD);
				return con;
			}

	protected void closeResultSet(ResultSet rs) throws SQLException {
		rs.close();
	}

}