package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Funcao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer ID_FUNCAO;
	private String NOME;
	private String CRITICIDADE;
	private String DESCRICAO;
	private String EXAMES;
	private Integer ID_EXAME;
	

	public Funcao() {
	}

	public Funcao(Integer ID_FUNCAO, String NOME, String CRITICIDADE,
			String DESCRICAO, String EXAMES) {

		this.ID_FUNCAO = ID_FUNCAO;
		this.NOME = NOME;
		this.CRITICIDADE =CRITICIDADE;
		this.DESCRICAO = DESCRICAO;
		this.EXAMES = EXAMES;
	}

	public Integer getID_FUNCAO() {
		return ID_FUNCAO;
	}

	public void setID_FUNCAO(Integer ID_FUNCAO) {
		this.ID_FUNCAO = ID_FUNCAO;
	}

	public String getNOME() {
		return NOME;
	}

	public void setNOME(String NOME) {
		this.NOME = NOME;
	}

	public String getCRITICIDADE() {
		return CRITICIDADE;
	}

	public void setCRITICIDADE(String cRITICIDADE) {
		CRITICIDADE = cRITICIDADE;
	}

	public String getEXAMES() {
		return EXAMES;
	}

	public void setEXAMES(String eXAMES) {
		EXAMES = eXAMES;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public Integer getID_EXAME() {
		return ID_EXAME;
	}

	public void setID_EXAME(Integer iD_EXAME) {
		ID_EXAME = iD_EXAME;
	}

	
	
}