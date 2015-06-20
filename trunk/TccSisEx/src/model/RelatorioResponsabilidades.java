package model;

import java.io.Serializable;
import java.util.Date;

public class RelatorioResponsabilidades implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeSetor;
	private int pendencias;
	private String nomeFuncionario;
	private Date dataInicial;
	private Date dataFinal;
	
	
	public  RelatorioResponsabilidades(){
		
	}
	
	public String getNomeSetor() {
		return nomeSetor;
	}
	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}
	public int getPendencias() {
		return pendencias;
	}
	public void setPendencias(int pendencias) {
		this.pendencias = pendencias;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
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
