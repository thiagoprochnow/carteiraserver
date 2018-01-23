package br.com.guiainvestimento.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TesouroService {
	private TesouroDAO db = new TesouroDAO();
	public List<Tesouro> getTesouros(){
		try {
			List<Tesouro> tesouros = db.getTesouros();
			return tesouros;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Tesouro>();
		}
	}
	
	public Tesouro getTesouro(Long id) {
		try {
			return db.getTesouroById(id);
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
	
	public boolean save(Tesouro tesouro) {
		try {
			db.save(tesouro);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Tesouro> findByName(String name){
		try {
			return db.findByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
