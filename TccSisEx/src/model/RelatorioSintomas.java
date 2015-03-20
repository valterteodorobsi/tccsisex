package model;

import java.io.Serializable;

public class RelatorioSintomas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeSintomas;
	private String nomeSetor;
	private float qtdSintomas;
	
	
	
	public RelatorioSintomas(){
		
	}
	
	
	public RelatorioSintomas(String nomeSintomas, String nomeSetor,
			float qtdSintomas) {
		
		this.nomeSintomas = nomeSintomas;
		this.nomeSetor = nomeSetor;
		this.qtdSintomas = qtdSintomas;
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
	
	
	
	
	

}
