package br.com.guiainvestimento.domain;

import java.io.Serializable;

public class FundGain implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cnpj;
	private String rate;
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}

}
