package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Exame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer ID_EXAME;
	private String NOME_EXAME;
	private String DESCRICAO;
	private String VALIDADE;
	

	public Exame() {
	}

	public Exame(Integer ID_EXAME, String NOME_EXAME, String DESCRICAO, String VALIDADE) {

		this.ID_EXAME = ID_EXAME;
		this.NOME_EXAME = NOME_EXAME;
		this.DESCRICAO = DESCRICAO;
		this.VALIDADE = VALIDADE;
	}

	public Integer getID_EXAME() {
		return ID_EXAME;
	}

	public void setID_EXAME(Integer ID_EXAME) {
		this.ID_EXAME = ID_EXAME;
	}

	public String getNOME_EXAME() {
		return NOME_EXAME;
	}

	public void setNOME_EXAME(String nOME_EXAME) {
		NOME_EXAME = nOME_EXAME;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public String getVALIDADE() {
		return VALIDADE;
	}

	public void setVALIDADE(String vALIDADE) {
		VALIDADE = vALIDADE;
	}


}