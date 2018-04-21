package br.com.guiainvestimento.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IpcaService {
	private IpcaDAO db = new IpcaDAO();
	public List<Ipca> getIpcas(){
		try {
			List<Ipca> ipcas = db.getIpcas();
			return ipcas;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Ipca>();
		}
	}
	
	public Ipca getIpca(Long id) {
		try {
			return db.getIpcaById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean save(Ipca ipca) {
		try {
			db.save(ipca);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
