package br.com.guiainvestimento.domain;

import java.io.Serializable;

public class Cdi implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private double valor;
	private Long data;
	private String data_string;
	private String atualizado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Long getData() {
		return data;
	}
	public void setData(Long data) {
		this.data = data;
	}
	
	public String getDataString() {
		return data_string;
	}
	public void setDataString(String data_string) {
		this.data_string = data_string;
	}
	public String getAtualizado() {
		return atualizado;
	}
	public void setAtualizado(String atualizado) {
		this.atualizado = atualizado;
	}
	
	@Override
	public String toString() {
		return "Tesouro [id= " + id + ", valor= " + valor + ", data= " + data + ", atualizado= " + atualizado + "]";
	}
}
