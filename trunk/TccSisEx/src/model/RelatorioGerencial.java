package model;

import java.io.Serializable;

public class RelatorioGerencial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomeMedico;
	private String tipoEntrada;
	private float qtdeEntrada;
	
	
	
	public RelatorioGerencial() {
		
	}
	
	public RelatorioGerencial(String nomeMedico, String tipoEntrada,float qtdeEntrada) {
		this.nomeMedico = nomeMedico;
		this.tipoEntrada = tipoEntrada;
		this.qtdeEntrada = qtdeEntrada;
	}
	public String getNomeMedico() {
		return nomeMedico;
	}
	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}
	public String getTipoEntrada() {
		return tipoEntrada;
	}
	public void setTipoEntrada(String tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
	}
	public float getQtdeEntrada() {
		return qtdeEntrada;
	}
	public void setQtdeEntrada(float qtdeEntrada) {
		this.qtdeEntrada = qtdeEntrada;
	}
	

	
}
