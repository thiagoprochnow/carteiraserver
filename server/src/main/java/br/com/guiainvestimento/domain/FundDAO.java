package br.com.guiainvestimento.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FundDAO extends BaseDAO{
	public Fund getFundById(Long id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from cadastro_fundo where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Fund fund = createFund(rs);
				rs.close();
				return fund;
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
	
	public List<Fund> findByName(String[] names) throws SQLException {
		List<Fund> funds = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "select * from cadastro_fundo where denominacao like ?";
			for(int count=1 ; count< names.length ; count++){
                sql += " AND denominacao like ?";
            }
			stmt = conn.prepareStatement(sql);
			for(int count=0 ; count< names.length ; count++){
				stmt.setString(count+1, "%" + names[count] + "%");
            }
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Fund fund = createFund(rs);
				funds.add(fund);
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
		return funds;
	}
	
	public List<Fund> findByCnpj(String cnpj) throws SQLException {
		List<Fund> funds = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from cadastro_fundo where cnpj like ?");
			stmt.setString(1, "%" + cnpj + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Fund fund = createFund(rs);
				funds.add(fund);
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
		return funds;
	}
	
	public List<Fund> getFunds() throws SQLException {
		List<Fund> funds = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from cadastro_fundo");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Fund fund = createFund(rs);
				funds.add(fund);
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
		return funds;
	}
	
	public Fund createFund(ResultSet rs) throws SQLException{
		Fund fund = new Fund();
		fund.setId(rs.getLong("id"));
		fund.setCnpj(rs.getString("cnpj"));
		fund.setNome(rs.getString("denominacao"));
		fund.setTipo(rs.getString("classe"));
		return fund;
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
