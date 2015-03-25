package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FuncionarioPes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer ID_MATRICULA;
	private String NOME;
	private Integer ID_CENTRO_CUSTO;
	private Integer ID_FUNCAO;
	private Date DATA_NASC;
	private String RG;
	private String ENDERECO;
	private String EMAIL;
	private String SEXO;
	private Integer RAMAL;
	private Date DATA_CAD;
	private String NOMESET;
	private String NOMEFUN;
	
	

	public FuncionarioPes() {
	}

	public FuncionarioPes(Integer ID_MATRICULA, String NOME, Integer iD_CENTRO_CUSTO,
			Integer ID_FUNCAO, Date DATA_NASC, String RG, String ENDERECO,
			String EMAIL, String SEXO, Integer RAMAL, Date DATA_CAD) {
		
		this.ID_MATRICULA = ID_MATRICULA;
		this.NOME = NOME;
		this.ID_CENTRO_CUSTO = iD_CENTRO_CUSTO;
		this.ID_FUNCAO = ID_FUNCAO;
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

	public Integer getID_CENTRO_CUSTO() {
		return ID_CENTRO_CUSTO;
	}

	public void setID_CENTRO_CUSTO(Integer iD_CENTRO_CUSTO) {
		ID_CENTRO_CUSTO = iD_CENTRO_CUSTO;
	}

	public Integer getID_FUNCAO() {
		return ID_FUNCAO;
	}

	public void setID_FUNCAO(Integer ID_fUNCAO) {
		ID_FUNCAO = ID_fUNCAO;
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

	public String getNOMESET() {
		return NOMESET;
	}

	public void setNOMESET(String nOMESET) {
		NOMESET = nOMESET;
	}

	public String getNOMEFUN() {
		return NOMEFUN;
	}

	public void setNOMEFUN(String nOMEFUN) {
		NOMEFUN = nOMEFUN;
	}
	
}