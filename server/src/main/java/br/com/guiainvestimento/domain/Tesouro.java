package br.com.guiainvestimento.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tesouro implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private double valor;
	private String data;
	private String tipo;
	private String atualizado;
	private String error;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getAtualizado() {
		return atualizado;
	}
	public void setAtualizado(String atualizado) {
		this.atualizado = atualizado;
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "Tesouro [id= " + id + ", nome= " + nome + ", valor= " + valor + ", data= " + data + ", tipo= " + tipo + ", atualizado= " + atualizado + "]";
	}
}
