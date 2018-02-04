package br.com.guiainvestimento.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public BaseDAO() {
		try {
			// Necessario para utilizar o driver JDBC do MySQL
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() throws SQLException {
		// URL de conexão com o banco de dados
		String url = "jdbc:mysql://35.199.125.153:3306/guiainvestimento";
		//String url = "jdbc:mysql://localhost/guiainvestimento";
		// Conecta utilizando a URL, usuario e senha
		Connection conn = DriverManager.getConnection(url, "guiauser", "thp1133");
		//Connection conn = DriverManager.getConnection(url, "guiauser", "user1133");
		return conn;
	}
	
	public static void main(String[] args) throws SQLException {
		BaseDAO db = new BaseDAO();
		Connection conn = db.getConnection();
		System.out.println(conn);
	}
}
