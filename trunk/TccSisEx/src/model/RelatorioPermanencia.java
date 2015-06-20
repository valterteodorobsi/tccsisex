package model;

import java.io.Serializable;
import java.util.Date;

public class RelatorioPermanencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nomeFuncionario;
	private float tempoMedidoPermanenciaMinutos;
	private float tempoMedioPermanenciaHoras;
	private Integer QtdEntrada;
	private Date dataInicial;
	private Date dataFinal;
	
	

	public RelatorioPermanencia() {
		
	}




	public String getNomeFuncionario() {
		return nomeFuncionario;
	}




	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}




	public float getTempoMedidoPermanenciaMinutos() {
		return tempoMedidoPermanenciaMinutos;
	}




	public void setTempoMedidoPermanenciaMinutos(float tempoMedidoPermanenciaMinutos) {
		this.tempoMedidoPermanenciaMinutos = tempoMedidoPermanenciaMinutos;
	}




	public float getTempoMedioPermanenciaHoras() {
		return tempoMedioPermanenciaHoras;
	}




	public void setTempoMedioPermanenciaHoras(float tempoMedioPermanenciaHoras) {
		this.tempoMedioPermanenciaHoras = tempoMedioPermanenciaHoras;
	}




	public Integer getQtdEntrada() {
		return QtdEntrada;
	}




	public void setQtdEntrada(Integer qtdEntrada) {
		QtdEntrada = qtdEntrada;
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
