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
	private Integer idColaborador;
	private Integer idSetor;
	
	public RelatorioAtestado(){
		
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



	public Integer getIdColaborador() {
		return idColaborador;
	}



	public void setIdColaborador(Integer idColaborador) {
		this.idColaborador = idColaborador;
	}



	public Integer getIdSetor() {
		return idSetor;
	}



	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
	}
	
	
	
	
}
