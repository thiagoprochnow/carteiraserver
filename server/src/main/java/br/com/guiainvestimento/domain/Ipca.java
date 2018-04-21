package br.com.guiainvestimento.domain;

import java.io.Serializable;

public class Ipca implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private int ano;
	private int mes;
	// Para cada series de mes, 1 mes, 3 meses, 6 meses, 12 meses e no ano
	private double um;
	private double tres;
	private double seis;
	private double doze;
	private double anual;
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
	public double getUm() {
		return um;
	}
	public void setUm(double um) {
		this.um = um;
	}
	public double getTres() {
		return tres;
	}
	public void setTres(double tres) {
		this.tres = tres;
	}
	public double getSeis() {
		return seis;
	}
	public void setSeis(double seis) {
		this.seis = seis;
	}
	public double getDoze() {
		return doze;
	}
	public void setDoze(double doze) {
		this.doze = doze;
	}
	public double getAnual() {
		return anual;
	}
	public void setAnual(double anual) {
		this.anual = anual;
	}
	public String getAtualizado() {
		return atualizado;
	}
	public void setAtualizado(String atualizado) {
		this.atualizado = atualizado;
	}
}
