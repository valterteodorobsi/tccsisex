package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SOLICITACAO_EXAME")
public class PedidoDeExame implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ID_SOLIC_EXA;
	private String NOME_COLABORADOR;
	private Date DATA_NASC;
	private Integer MATRICULA;
	private String SETOR;
	private String FUNCAO;
	private String NOME_EXAME;
	private String CID;
	private String DESCRICAO;
	private String NOME_MEDICO;
	
	public PedidoDeExame(){
		
		
	}

	public Integer getID_SOLIC_EXA() {
		return ID_SOLIC_EXA;
	}

	public void setID_SOLIC_EXA(Integer iD_SOLIC_EXA) {
		ID_SOLIC_EXA = iD_SOLIC_EXA;
	}

	public String getNOME_COLABORADOR() {
		return NOME_COLABORADOR;
	}

	public void setNOME_COLABORADOR(String nOME_COLABORADOR) {
		NOME_COLABORADOR = nOME_COLABORADOR;
	}

	public Date getDATA_NASC() {
		return DATA_NASC;
	}

	public void setDATA_NASC(Date dATA_NASC) {
		DATA_NASC = dATA_NASC;
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

	public String getFUNCAO() {
		return FUNCAO;
	}

	public void setFUNCAO(String fUNCAO) {
		FUNCAO = fUNCAO;
	}

	public String getNOME_EXAME() {
		return NOME_EXAME;
	}

	public void setNOME_EXAME(String nOME_EXAME) {
		NOME_EXAME = nOME_EXAME;
	}

	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
		CID = cID;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public String getNOME_MEDICO() {
		return NOME_MEDICO;
	}

	public void setNOME_MEDICO(String nOME_MEDICO) {
		NOME_MEDICO = nOME_MEDICO;
	}
	
	
	
}
