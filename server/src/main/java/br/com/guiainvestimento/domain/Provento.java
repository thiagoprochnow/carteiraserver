package br.com.guiainvestimento.domain;

import java.io.Serializable;

public class Provento implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String codigo;
	private String data;
	private long timestamp;
	private double valor = 0;
	private String tipo;
	private String atualizado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getAtualizado() {
		return atualizado;
	}
	public void setAtualizado(String atualizado) {
		this.atualizado = atualizado;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
