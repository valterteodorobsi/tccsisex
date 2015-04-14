package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Medico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer ID_MATRICULA;
	private String CRM;
	private String NOME;
	private Date DATA_NASC;
	private String RG;
	private String ESPECIALIDADE;
	private Integer CAR_HOR_SEMANA;
	private String DIAS_EXPEDIENTE;
	private Boolean ATIVO;
	
	
	public Medico() {
	}

	public Medico(Integer ID_MATRICULA, String CRM, String NOME,
			 Date DATA_NASC, String RG, String ESPECIALIDADE,
			 Integer CAR_HOR_SEMANA , String DIAS_EXPEDIENTE) {
		
		this.ID_MATRICULA = ID_MATRICULA;
		this.NOME = NOME;
		this.CRM = CRM;
		this.DATA_NASC = DATA_NASC;
		this.RG = RG;
		this.ESPECIALIDADE = ESPECIALIDADE;
		this.CAR_HOR_SEMANA = CAR_HOR_SEMANA;
		this.DIAS_EXPEDIENTE = DIAS_EXPEDIENTE;
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

	public String getCRM() {
		return CRM;
	}

	public void setCRM(String CRM) {
		this.CRM = CRM;
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

	public String getESPECIALIDADE() {
		return ESPECIALIDADE;
	}

	public void setESPECIALIDADE(String ESPECIALIDADE) {
		this.ESPECIALIDADE = ESPECIALIDADE;
	}

	public String getDIAS_EXPEDIENTE() {
		return DIAS_EXPEDIENTE;
	}

	public void setDIAS_EXPEDIENTE(String DIAS_EXPEDIENTE) {
		this.DIAS_EXPEDIENTE = DIAS_EXPEDIENTE;
	}


	public Integer getCAR_HOR_SEMANA() {
		return CAR_HOR_SEMANA;
	}

	public void setCAR_HOR_SEMANA(Integer CAR_HOR_SEMANA) {
		this.CAR_HOR_SEMANA = CAR_HOR_SEMANA;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	
}