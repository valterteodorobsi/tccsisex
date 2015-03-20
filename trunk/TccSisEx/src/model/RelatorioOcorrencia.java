package model;

import java.io.Serializable;

public class RelatorioOcorrencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeColaborador;
	private String nomeSetor;
	private float qtdEncaminhamento;
	private String encaminhamento;
	
	
	
	public RelatorioOcorrencia (){
		
	}



	public String getNomeColaborador() {
		return nomeColaborador;
	}



	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}



	
	public String getNomeSetor() {
		return nomeSetor;
	}



	
	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}



	public float getQtdEncaminhamento() {
		return qtdEncaminhamento;
	}



	public void setQtdEncaminhamento(float qtdEncaminhamento) {
		this.qtdEncaminhamento = qtdEncaminhamento;
	}



	public String getEncaminhamento() {
		return encaminhamento;
	}



	public void setEncaminhamento(String encaminhamento) {
		this.encaminhamento = encaminhamento;
	}
	
	
	
}
