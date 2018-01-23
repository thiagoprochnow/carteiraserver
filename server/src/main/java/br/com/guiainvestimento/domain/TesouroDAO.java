package br.com.guiainvestimento.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TesouroDAO extends BaseDAO{
	public Tesouro getTesouroById(Long id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from tesouro where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Tesouro tesouro = createTesouro(rs);
				rs.close();
				return tesouro;
			}
		} finally {
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public List<Tesouro> findByName(String name) throws SQLException {
		List<Tesouro> tesouros = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from tesouro where lower(nome) like ?");
			stmt.setString(1, "%" + name.toLowerCase() + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Tesouro tesouro = createTesouro(rs);
				tesouros.add(tesouro);
			}
			rs.close();
		} finally {
			if(stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return tesouros;
	}
	
	public List<Tesouro> getTesouros() throws SQLException {
		List<Tesouro> tesouros = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from tesouro");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Tesouro tesouro = createTesouro(rs);
				tesouros.add(tesouro);
			}
			rs.close();
		} finally {
			if(stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return tesouros;
	}
	
	public Tesouro createTesouro(ResultSet rs) throws SQLException{
		Tesouro tesouro = new Tesouro();
		tesouro.setId(rs.getLong("id"));
		tesouro.setNome(rs.getString("nome"));
		tesouro.setValor(rs.getDouble("valor"));
		tesouro.setData(rs.getString("data_fim"));
		tesouro.setTipo(rs.getString("tipo"));
		return tesouro;
	}
	
	public void save(Tesouro tesouro) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if(tesouro.getId() == null) {
				stmt = conn.prepareStatement("insert into tesouro (nome,valor,data_fim,tipo) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("update tesouro set nome=?,valor=?,data_fim=?,tipo=? where id=?");
			}
			stmt.setString(1, tesouro.getNome());
			stmt.setDouble(2, tesouro.getValor());
			stmt.setString(3, tesouro.getData());
			stmt.setString(4, tesouro.getTipo());
			if(tesouro.getId() != null) {
				// Update
				stmt.setLong(5, tesouro.getId());
			}
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir o carro");
			}
			if(tesouro.getId() == null) {
				Long id = getGeneratedId(stmt);
				tesouro.setId(id);
			}
		} finally {
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	
	// id gerado com o campo auto incremento
	public static Long getGeneratedId(Statement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		if(rs.next()) {
			Long id = rs.getLong(1);
			return id;
		}
		return 0L;
	}
	
	public boolean delete(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("delete from tesouro where id=?");
			stmt.setLong(1, id);
			int count = stmt.executeUpdate();
			boolean ok = count > 0;
			return ok;
		} finally {
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
}
