package br.com.guiainvestimento.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProventoDAO extends BaseDAO{
	public Provento getProventoById(Long id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from provento where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Provento provento = createProvento(rs);
				rs.close();
				return provento;
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
	
	public List<Provento> findByData(String data) throws SQLException {
		List<Provento> proventos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from provento where data_string like ?");
			stmt.setString(1, "%" + data + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Provento provento = createProvento(rs);
				proventos.add(provento);
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
		return proventos;
	}
	
	public List<Provento> getProventos() throws SQLException {
		List<Provento> proventos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from provento");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Provento provento = createProvento(rs);
				proventos.add(provento);
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
		return proventos;
	}
	
	public List<Provento> getProventosByCodigo(String codigo) throws SQLException {
		List<Provento> proventos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from provento where codigo like ?");
			stmt.setString(1, "%" + codigo + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Provento provento = createProvento(rs);
				proventos.add(provento);
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
		return proventos;
	}
	
	public List<Provento> getProventoByDate(Long timestamp, String codigo) throws SQLException {
		List<Provento> proventos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from provento where codigo like ? and data > ? order by data asc");
			stmt.setString(1, "%" + codigo + "%");
			stmt.setLong(2, timestamp);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Provento provento = createProvento(rs);
				proventos.add(provento);
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
		return proventos;
	}
	
	public Provento createProvento(ResultSet rs) throws SQLException{
		Provento provento = new Provento();
		provento.setId(rs.getLong("id"));
		provento.setValor(rs.getDouble("valor"));
		provento.setData(rs.getString("data_string"));
		provento.setTimestamp(rs.getLong("data"));
		provento.setAtualizado(rs.getString("atualizado"));
		return provento;
	}
	
	public void save(Provento provento) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if(provento.getId() == null) {
				stmt = conn.prepareStatement("insert into provento (codigo,data,data_string,valor,tipo,atualizado) VALUES(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("update provento set codigo=?,data=?,data_string=?,valor=?,tipo=?,atualizado=? where id=?");
			}
			stmt.setString(1, provento.getCodigo());
			stmt.setLong(2, provento.getTimestamp());
			stmt.setString(3, provento.getData());
			stmt.setDouble(4, provento.getValor());
			stmt.setString(5, provento.getTipo());
			stmt.setString(6, provento.getAtualizado());
			if(provento.getId() != null) {
				// Update
				stmt.setLong(7, provento.getId());
			}
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir o provento");
			}
			if(provento.getId() == null) {
				Long id = getGeneratedId(stmt);
				provento.setId(id);
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
}
