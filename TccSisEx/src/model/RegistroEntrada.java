package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REGISTRO_ENTRADA")
public class RegistroEntrada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer ID_REGISTRO;
	private Integer ID_MATRICULA;
	private Integer ID_MEDICO;
	private Integer ID_REMEDIO;
	private Integer ID_SINTOMAS;
	private Date DATA_ENTRADA;
	private Date DATA_SAIDA;
	private String DESCRICAO;
	@Column(name = "TIPO_ENTRADA") 
	private boolean emergencial;
	@Column(name = "ENC_EX")  
	private String encExt;
	private String NOME_COLABORADOR;
	private String MEDICO;
	private String SINTOMAS;
	private String REMEDIO;

	

	
	public RegistroEntrada() {

	}

	public RegistroEntrada(Integer iD_REGISTRO, String dESCRICAO,
			String rEMEDIO, Integer iD_MATRICULA, Date dATA_ENTRADA,
			Date dATA_SAIDA, String NOME_COLABORADOR, String SINTOMAS) {

		this.ID_REGISTRO = iD_REGISTRO;
		this.DESCRICAO = dESCRICAO;
		this.REMEDIO = rEMEDIO;
		this.ID_MATRICULA = iD_MATRICULA;
		this.DATA_ENTRADA = dATA_ENTRADA;
		this.DATA_SAIDA = dATA_SAIDA;
		this.NOME_COLABORADOR = NOME_COLABORADOR;
		this.SINTOMAS = SINTOMAS;
	}

	public Integer getID_REGISTRO() {
		return ID_REGISTRO;
	}

	public void setID_REGISTRO(Integer iD_REGISTRO) {
		ID_REGISTRO = iD_REGISTRO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public String getREMEDIO() {
		return REMEDIO;
	}

	public void setREMEDIO(String rEMEDIO) {
		REMEDIO = rEMEDIO;
	}

	public Integer getID_MATRICULA() {
		return ID_MATRICULA;
	}

	public void setID_MATRICULA(Integer iD_MATRICULA) {
		ID_MATRICULA = iD_MATRICULA;
	}

	public Date getDATA_ENTRADA() {
		return DATA_ENTRADA;
	}

	public void setDATA_ENTRADA(Date dATA_ENTRADA) {
		DATA_ENTRADA = dATA_ENTRADA;
	}

	public Date getDATA_SAIDA() {
		return DATA_SAIDA;
	}

	public void setDATA_SAIDA(Date dATA_SAIDA) {
		DATA_SAIDA = dATA_SAIDA;
	}

	public String getNOME_COLABORADOR() {
		return NOME_COLABORADOR;
	}

	public void setNOME_COLABORADOR(String nOME_COLABORADOR) {
		NOME_COLABORADOR = nOME_COLABORADOR;
	}
	public String getEncExt() {
		return encExt;
	}

	public void setEncExt(String encExt) {
		this.encExt = encExt;
	}
	public boolean isEmergencial() {
		return emergencial;
	}

	public void setEmergencial(boolean emergencial) {
		this.emergencial = emergencial;
	}

	public String getSINTOMAS() {
		return SINTOMAS;
	}

	public void setSINTOMAS(String sINTOMAS) {
		SINTOMAS = sINTOMAS;
	}

	public Integer getID_MEDICO() {
		return ID_MEDICO;
	}

	public void setID_MEDICO(Integer iD_MEDICO) {
		ID_MEDICO = iD_MEDICO;
	}

	public Integer getID_REMEDIO() {
		return ID_REMEDIO;
	}

	public void setID_REMEDIO(Integer iD_REMEDIO) {
		ID_REMEDIO = iD_REMEDIO;
	}

	public Integer getID_SINTOMAS() {
		return ID_SINTOMAS;
	}

	public void setID_SINTOMAS(Integer iD_SINTOMAS) {
		ID_SINTOMAS = iD_SINTOMAS;
	}

	public String getMEDICO() {
		return MEDICO;
	}

	public void setMEDICO(String mEDICO) {
		MEDICO = mEDICO;
	}
	
	
}
