package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static String URL = "jdbc:mysql://localhost:3308/MaisPraTi";
	private static String USER = "root";
	private static String PASSWORD = "";
	
	
	public Connection getConexao() throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		}catch(SQLException e) {
			throw new SQLException("Erro de conexão: " + e.getCause());
		}
		
		return connection;
	}
}
