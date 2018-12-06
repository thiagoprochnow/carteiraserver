package br.com.guiainvestimento.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FundService {
	private FundDAO db = new FundDAO();
	public List<Fund> getFunds(){
		try {
			List<Fund> funds = db.getFunds();
			return funds;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Fund>();
		}
	}
	
	public Fund getFund(Long id) {
		try {
			return db.getFundById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Fund> findByName(String[] name){
		try {
			return db.findByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Fund> findByCnpj(String cnpj){
		try {
			return db.findByCnpj(cnpj);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
