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
	private Integer ID_SETOR;
	private Integer ID_FUNCAO;
	private Integer ID_MEDICO;
	private Integer ID_EXAME;
	private Integer MATRICULA;
	private String CID;
	private String DESCRICAO;
	private Date DATA_NASC;
	private String NOME_MEDICO;
	private String NOME_COLABORADOR;
	private String NOME_EXAME;
	private String SETOR;
	private String FUNCAO;
	
	public PedidoDeExame(){
		
		
	}

	public Integer getID_SOLIC_EXA() {
		return ID_SOLIC_EXA;
	}

	public void setID_SOLIC_EXA(Integer iD_SOLIC_EXA) {
		ID_SOLIC_EXA = iD_SOLIC_EXA;
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

	public Integer getID_SETOR() {
		return ID_SETOR;
	}

	public void setID_SETOR(Integer iD_SETOR) {
		ID_SETOR = iD_SETOR;
	}

	public Integer getID_FUNCAO() {
		return ID_FUNCAO;
	}

	public void setID_FUNCAO(Integer iD_FUNCAO) {
		ID_FUNCAO = iD_FUNCAO;
	}

	public Integer getID_MEDICO() {
		return ID_MEDICO;
	}

	public void setID_MEDICO(Integer iD_MEDICO) {
		ID_MEDICO = iD_MEDICO;
	}

	public Integer getID_EXAME() {
		return ID_EXAME;
	}

	public void setID_EXAME(Integer iD_EXAME) {
		ID_EXAME = iD_EXAME;
	}

	public String getNOME_MEDICO() {
		return NOME_MEDICO;
	}

	public void setNOME_MEDICO(String nOME_MEDICO) {
		NOME_MEDICO = nOME_MEDICO;
	}

	public String getNOME_COLABORADOR() {
		return NOME_COLABORADOR;
	}

	public void setNOME_COLABORADOR(String nOME_COLABORADOR) {
		NOME_COLABORADOR = nOME_COLABORADOR;
	}

	public String getNOME_EXAME() {
		return NOME_EXAME ;
	}

	public void setNOME_EXAME(String nOME_EXAME) {
		NOME_EXAME = nOME_EXAME;
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

	
	
	
}
