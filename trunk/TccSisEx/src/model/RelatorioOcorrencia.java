package model;

import java.io.Serializable;
import java.util.Date;

public class RelatorioOcorrencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeColaborador;
	private String nomeSetor;
	private float qtdEncaminhamento;
	private String encaminhamento;
	private Integer idMatricula;
	private Integer idCentroCusto;
	private Date dataInicial;
	private Date dataFinal;
	
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



	public Integer getIdMatricula() {
		return idMatricula;
	}



	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}



	public Integer getIdCentroCusto() {
		return idCentroCusto;
	}



	public void setIdCentroCusto(Integer idCentroCusto) {
		this.idCentroCusto = idCentroCusto;
	}
	
	public Date getDataFinal() {
		return dataFinal;
	}


	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public Date getDataInicial() {
		return dataInicial;
	}


	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	
}
