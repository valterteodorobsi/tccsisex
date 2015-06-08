package model;

import java.io.Serializable;

public class RelatorioResponsabilidades implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeSetor;
	private int pendencias;
	private String nomeFuncionario;
	
	
	
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
	
	
	
	
	
	
}
