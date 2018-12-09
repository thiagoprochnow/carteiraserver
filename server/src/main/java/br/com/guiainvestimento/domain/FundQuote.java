package br.com.guiainvestimento.domain;

import java.io.Serializable;

public class FundQuote implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String cnpj;
	private String data;
	private String quote;
	
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
}
