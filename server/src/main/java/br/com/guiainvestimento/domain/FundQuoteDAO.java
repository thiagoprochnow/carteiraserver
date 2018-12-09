package br.com.guiainvestimento.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FundQuoteDAO extends BaseDAO{
	public FundQuote getFundQuoteById(Long id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from cotas_fundos where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				FundQuote fund = createFundQuote(rs);
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
	
	public List<FundQuote> findByCnpj(String cnpj) throws SQLException {
		List<FundQuote> funds = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from cotas_fundos where cnpj = ? order by data asc");
			stmt.setString(1, cnpj);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FundQuote fund = createFundQuote(rs);
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
	
	public List<FundQuote> findByCnpjData(String cnpj, Long timestamp) throws SQLException {
		List<FundQuote> funds = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		Timestamp ts = new Timestamp(timestamp*1000);
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from cotas_fundos where cnpj = ? AND data > ? order by data asc");
			stmt.setString(1, cnpj);
			stmt.setTimestamp(2, ts);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FundQuote fund = createFundQuote(rs);
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
	
	public FundQuote findByCnpjLatest(String cnpj) throws SQLException {
		FundQuote fund = new FundQuote();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from cotas_fundos where cnpj = ? order by data desc LIMIT 1");
			stmt.setString(1, cnpj);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				fund = createFundQuote(rs);
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
		return fund;
	}
	
	public List<FundQuote> getFundQuotes() throws SQLException {
		List<FundQuote> funds = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from cotas_fundos");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FundQuote fund = createFundQuote(rs);
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
	
	public FundQuote createFundQuote(ResultSet rs) throws SQLException{
		FundQuote fund = new FundQuote();
		fund.setId(rs.getLong("id"));
		fund.setCnpj(rs.getString("cnpj"));
		fund.setData(rs.getString("data"));
		fund.setQuote(rs.getString("cota"));
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
