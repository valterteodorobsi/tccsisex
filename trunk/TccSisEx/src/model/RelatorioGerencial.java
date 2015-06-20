package model;

import java.io.Serializable;
import java.util.Date;

public class RelatorioGerencial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer nomeMedico;
	private String tipoEntrada;
	private float qtdeEntrada;
	private String nomeMedicos;
	private Date dataInicial;
	private Date dataFinal;
	
	
	public RelatorioGerencial() {
		
	}
	
	public RelatorioGerencial(int nomeMedico, String tipoEntrada,float qtdeEntrada) {
		this.nomeMedico = nomeMedico;
		this.tipoEntrada = tipoEntrada;
		this.qtdeEntrada = qtdeEntrada;
	}
	public Integer getNomeMedico() {
		return nomeMedico;
	}
	public void setNomeMedico(Integer nomeMedico) {
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

	public String getNomeMedicos() {
		return nomeMedicos;
	}

	public void setNomeMedicos(String nomeMedicos) {
		this.nomeMedicos = nomeMedicos;
	}
	
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	
}
