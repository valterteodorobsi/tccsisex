package model;

import java.io.Serializable;

public class RelatorioSintomas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idSintomas;
	private Integer idSetor;
	private String nomeSintomas;
	private String nomeSetor;
	private float qtdSintomas;
	
	
	
	public RelatorioSintomas(){
		
	}
	
	
	public String getNomeSintomas() {
		return nomeSintomas;
	}
	public void setNomeSintomas(String nomeSintomas) {
		this.nomeSintomas = nomeSintomas;
	}
	public String getNomeSetor() {
		return nomeSetor;
	}
	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}
	public float getQtd_sintomas() {
		return qtdSintomas;
	}
	public void setQtd_sintomas(float qtdSintomas) {
		this.qtdSintomas = qtdSintomas;
	}


	public Integer getIdSintomas() {
		return idSintomas;
	}


	public void setIdSintomas(Integer idSintomas) {
		this.idSintomas = idSintomas;
	}


	public Integer getIdSetor() {
		return idSetor;
	}


	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
	}


	public float getQtdSintomas() {
		return qtdSintomas;
	}


	public void setQtdSintomas(float qtdSintomas) {
		this.qtdSintomas = qtdSintomas;
	}
	
	
	
	
	

}
