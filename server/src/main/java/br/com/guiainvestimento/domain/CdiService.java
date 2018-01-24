package br.com.guiainvestimento.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CdiService {
	private CdiDAO db = new CdiDAO();
	public List<Cdi> getCdis(){
		try {
			List<Cdi> tesouros = db.getCdis();
			return tesouros;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Cdi>();
		}
	}
	
	public Cdi getCdi(Long id) {
		try {
			return db.getCdiById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean delete(Long id) {
		try {
			return db.delete(id);
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean save(Cdi cdi) {
		try {
			db.save(cdi);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Cdi> findByData(String name){
		try {
			return db.findByData(name);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
