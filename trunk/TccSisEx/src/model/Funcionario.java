package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Funcionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer ID_MATRICULA;
	private String NOME;
	private String SETOR;
	private String FUNCAO;
	private Date DATA_NASC;
	private String RG;
	private String ENDERECO;
	private String EMAIL;
	private String SEXO;
	private Integer RAMAL;
	private Date DATA_CAD;
	
	
	
	public Funcionario() {
	}

	public Funcionario(Integer ID_MATRICULA, String NOME, String SETOR,
			String FUNCAO, Date DATA_NASC, String RG, String ENDERECO,
			String EMAIL, String SEXO, Integer RAMAL, Date DATA_CAD) {
		
		this.ID_MATRICULA = ID_MATRICULA;
		this.NOME = NOME;
		this.SETOR = SETOR;
		this.FUNCAO = FUNCAO;
		this.DATA_NASC = DATA_NASC;
		this.RG = RG;
		this.ENDERECO = ENDERECO;
		this.EMAIL = EMAIL;
		this.SEXO = SEXO;
		this.RAMAL = RAMAL;
	}

	public Integer getID_MATRICULA() {
		return ID_MATRICULA;
	}

	public void setID_MATRICULA(Integer ID_MATRICULA) {
		this.ID_MATRICULA = ID_MATRICULA;
	}

	public String getNOME() {
		return NOME;
	}

	public void setNOME(String NOME) {
		this.NOME = NOME;
	}

	public String getSETOR() {
		return SETOR;
	}

	public void setSETOR(String sETOR) {
		SETOR = sETOR;
	}

	public String getFUNCAO() {
		return FUNCAO;
	}

	public void setFUNCAO(String fUNCAO) {
		FUNCAO = fUNCAO;
	}

	public Date  getDATA_NASC() {
		return DATA_NASC;
	}

	public void setDATA_NASC(Date DATA_NASC) {
		this.DATA_NASC = DATA_NASC;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getENDERECO() {
		return ENDERECO;
	}

	public void setENDERECO(String eNDERECO) {
		ENDERECO = eNDERECO;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getSEXO() {
		return SEXO;
	}

	public void setSEXO(String sEXO) {
		SEXO = sEXO;
	}

	public Integer getRAMAL() {
		return RAMAL;
	}

	public void setRAMAL(Integer rAMAL) {
		RAMAL = rAMAL;
	}

	public Date getDATA_CAD() {
		return DATA_CAD;
	}

	public void setDATA_CAD(Date dATA_CAD) {
		DATA_CAD = dATA_CAD;
	}
}