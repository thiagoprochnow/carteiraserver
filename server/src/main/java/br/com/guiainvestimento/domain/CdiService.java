package br.com.guiainvestimento.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CdiService {
	private CdiDAO db = new CdiDAO();
	public List<Cdi> getCdis(){
		try {
			List<Cdi> cdis = db.getCdis();
			return cdis;
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
	
	public List<Cdi> getCdisByDate(Long timestamp){
		try {
			List<Cdi> cdis = db.getCdisByDate(timestamp);
			return cdis;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Cdi>();
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
