package br.com.guiainvestimento.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IpcaDAO extends BaseDAO{
	public Ipca getIpcaById(Long id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from ipca where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Ipca ipca = createIpca(rs);
				rs.close();
				return ipca;
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
	
	public List<Ipca> getIpcas() throws SQLException {
		List<Ipca> ipcas = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from ipca");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Ipca ipca = createIpca(rs);
				ipcas.add(ipca);
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
		return ipcas;
	}
	
	public Ipca createIpca(ResultSet rs) throws SQLException{
		Ipca ipca = new Ipca();
		ipca.setId(rs.getLong("id"));
		ipca.setAno(rs.getInt("ano"));
		ipca.setMes(rs.getInt("mes"));
		ipca.setUm(rs.getDouble("um"));
		ipca.setTres(rs.getDouble("tres"));
		ipca.setSeis(rs.getDouble("seis"));
		ipca.setDoze(rs.getDouble("doze"));
		ipca.setAnual(rs.getDouble("anual"));
		ipca.setAtualizado(rs.getString("atualizado"));
		return ipca;
	}
	
	public void save(Ipca ipca) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if(ipca.getId() == null) {
				stmt = conn.prepareStatement("insert into ipca (ano,mes,um,tres,seis,doze,anual,atualizado) VALUES(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("update ipca set ano=?,mes=?,um=?,tres=?,seis=?,doze=?,anual=?,atualizado=? where id=?");
			}
			stmt.setDouble(1, ipca.getAno());
			stmt.setDouble(2, ipca.getMes());
			stmt.setDouble(3, ipca.getUm());
			stmt.setDouble(4, ipca.getTres());
			stmt.setDouble(5, ipca.getSeis());
			stmt.setDouble(6, ipca.getDoze());
			stmt.setDouble(7, ipca.getAnual());
			stmt.setString(8, ipca.getAtualizado());
			if(ipca.getId() != null) {
				// Update
				stmt.setLong(9, ipca.getId());
			}
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir o ipca");
			}
			if(ipca.getId() == null) {
				Long id = getGeneratedId(stmt);
				ipca.setId(id);
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
