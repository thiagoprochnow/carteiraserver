package br.com.guiainvestimento.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="tesouros")
public class ListaTesouro implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Tesouro> tesouros;
	@XmlElement(name="tesouro")
	public List<Tesouro> getTesouros(){
		return tesouros;
	}
	
	public void setTesouros(List<Tesouro> tesouros) {
		this.tesouros = tesouros;
	}
}
