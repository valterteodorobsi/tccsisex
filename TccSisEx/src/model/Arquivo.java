package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name="ANEXO_EXAME")
public class Arquivo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ID_EXAME;
	@Lob  
	@Column(name = "IMAGEM")  
	private byte [] IMAGEM;
	private String NOME_COLABORADOR;
	private Integer MATRICULA;
	private String SETOR;
	private String CRITICIDADE;
	private String EXAME;
	private String DESCRICAO;
	private String VALIDADE;
	
	public String getVALIDADE() {
		return VALIDADE;
	}


	public void setVALIDADE(String vALIDADE) {
		VALIDADE = vALIDADE;
	}


	public Arquivo() {

	}
	
	
	public Integer getID_EXAME() {
		return ID_EXAME;
	}
	public void setID_EXAME(Integer iD_EXAME) {
		ID_EXAME = iD_EXAME;
	}
	public byte [] getIMAGEM() {
		return IMAGEM;
	}
	public void setIMAGEM(byte [] file) {
		IMAGEM = file;
	}
	public String getNOME_COLABORADOR() {
		return NOME_COLABORADOR;
	}
	public void setNOME_COLABORADOR(String NOME_COLABORADOR) {
		this.NOME_COLABORADOR = NOME_COLABORADOR;
	}
	public Integer getMATRICULA() {
		return MATRICULA;
	}
	public void setMATRICULA(Integer mATRICULA) {
		MATRICULA = mATRICULA;
	}
	public String getSETOR() {
		return SETOR;
	}
	public void setSETOR(String sETOR) {
		SETOR = sETOR;
	}
	public String getCRITICIDADE() {
		return CRITICIDADE;
	}
	public void setCRITICIDADE(String cRITICIDADE) {
		CRITICIDADE = cRITICIDADE;
	}
	public String getEXAME() {
		return EXAME;
	}
	public void setEXAME(String eXAME) {
		EXAME = eXAME;
	}
	public String getDESCRICAO() {
		return DESCRICAO;
	}
	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}
	
	
	
	
	
	
}
