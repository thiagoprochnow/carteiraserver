package br.com.guiainvestimento.domain;

import java.io.Serializable;

public class Ipca implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private int ano;
	private int mes;
	// Para cada series de mes, 1 mes, 3 meses, 6 meses, 12 meses e no ano
	private double valor;
	private String atualizado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}

	public String getAtualizado() {
		return atualizado;
	}
	public void setAtualizado(String atualizado) {
		this.atualizado = atualizado;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
}
