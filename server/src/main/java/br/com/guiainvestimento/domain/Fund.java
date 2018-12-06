package br.com.guiainvestimento.domain;

import java.io.Serializable;

public class Fund implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String cnpj;
	private String nome;
	private String tipo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "Fundo [id= " + id + ", cnpj= " + cnpj + ", nome= " + nome + ", tipo= " + tipo + "]";
	}
}
