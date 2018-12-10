package br.com.guiainvestimento.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FundQuoteService {
	private FundQuoteDAO db = new FundQuoteDAO();
	public List<FundQuote> getFundQuotes(){
		try {
			List<FundQuote> funds = db.getFundQuotes();
			return funds;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<FundQuote>();
		}
	}
	
	public FundQuote getFundQuote(Long id) {
		try {
			return db.getFundQuoteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<FundQuote> findByCnpj(String cnpj){
		try {
			return db.findByCnpj(cnpj);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<FundQuote> findByCnpjData(String cnpj, String data){
		try {
			return db.findByCnpjData(cnpj,data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public FundQuote findByCnpjLatest(String cnpj){
		try {
			return db.findByCnpjLatest(cnpj);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
