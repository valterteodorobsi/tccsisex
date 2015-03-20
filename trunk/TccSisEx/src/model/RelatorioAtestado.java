package model;

import java.io.Serializable;

public class RelatorioAtestado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeColaborador;
	private String nomeSetor;
	private float qtdAtestados;
	
	public RelatorioAtestado(){
		
	}
	
	public RelatorioAtestado(String nomeColaborador, String nomeSetor,
			float qtdAtestados) {
		super();
		this.nomeColaborador = nomeColaborador;
		this.nomeSetor = nomeSetor;
		this.qtdAtestados = qtdAtestados;
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



	public float getQtdAtestados() {
		return qtdAtestados;
	}



	public void setQtdAtestados(float qtdAtestados) {
		this.qtdAtestados = qtdAtestados;
	}
	
	
	
	
}
