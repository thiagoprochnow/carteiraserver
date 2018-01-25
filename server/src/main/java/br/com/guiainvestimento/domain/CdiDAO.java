package br.com.guiainvestimento.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CdiDAO extends BaseDAO{
	public Cdi getCdiById(Long id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from cdi where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Cdi cdi = createCdi(rs);
				rs.close();
				return cdi;
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
	
	public List<Cdi> findByData(String data) throws SQLException {
		List<Cdi> cdis = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from cdi where data_string like ?");
			stmt.setString(1, "%" + data + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cdi cdi = createCdi(rs);
				cdis.add(cdi);
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
		return cdis;
	}
	
	public List<Cdi> getCdis() throws SQLException {
		List<Cdi> cdis = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from cdi");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cdi cdi = createCdi(rs);
				cdis.add(cdi);
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
		return cdis;
	}
	
	public List<Cdi> getCdisByDate(Long timestamp) throws SQLException {
		List<Cdi> cdis = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from cdi where data > ? order by data asc");
			stmt.setLong(1, timestamp);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cdi cdi = createCdi(rs);
				cdis.add(cdi);
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
		return cdis;
	}
	
	public Cdi createCdi(ResultSet rs) throws SQLException{
		Cdi cdi = new Cdi();
		cdi.setId(rs.getLong("id"));
		cdi.setValor(rs.getDouble("valor"));
		cdi.setData(rs.getLong("data"));
		cdi.setDataString(rs.getString("data_string"));
		cdi.setAtualizado(rs.getString("atualizado"));
		return cdi;
	}
	
	public void save(Cdi cdi) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if(cdi.getId() == null) {
				stmt = conn.prepareStatement("insert into cdi (valor,data,data_string,atualizado) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("update cdi set valor=?,data=?,data_string=?,atualizado=? where id=?");
			}
			stmt.setDouble(1, cdi.getValor());
			stmt.setLong(2, cdi.getData());
			stmt.setString(3, cdi.getDataString());
			stmt.setString(4, cdi.getAtualizado());
			if(cdi.getId() != null) {
				// Update
				stmt.setLong(5, cdi.getId());
			}
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir o cdi");
			}
			if(cdi.getId() == null) {
				Long id = getGeneratedId(stmt);
				cdi.setId(id);
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
