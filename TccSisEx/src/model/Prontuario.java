package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Prontuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer ID_PRONTUARIO;
	private String PENDENCIAS;
	private String LINK_IMAGENS;
	private Integer ID_MATRICULA;
	private Integer ID_EXAME;
	private Integer ID_REGISTRO;
	
	
	

	public Prontuario(){
			
	}
	
	
	public Prontuario(Integer iD_PRONTUARIO, String pENDENCIAS,
			String lINK_IMAGENS, Integer iD_MATRICULA, Integer iD_EXAME,
			Integer iD_REGISTRO) {
		this.ID_PRONTUARIO = iD_PRONTUARIO;
		this.PENDENCIAS = pENDENCIAS;
		this.LINK_IMAGENS = lINK_IMAGENS;
		this.ID_MATRICULA = iD_MATRICULA;
		this.ID_EXAME = iD_EXAME;
		this.ID_REGISTRO = iD_REGISTRO;
	}
	

	
	
	public Integer getID_PRONTUARIO() {
		return ID_PRONTUARIO;
	}
	public void setID_PRONTUARIO(Integer iD_PRONTUARIO) {
		ID_PRONTUARIO = iD_PRONTUARIO;
	}
	public String getLINK_IMAGENS() {
		return LINK_IMAGENS;
	}
	public void setLINK_IMAGENS(String lINK_IMAGENS) {
		LINK_IMAGENS = lINK_IMAGENS;
	}
	public String getPENDENCIAS() {
		return PENDENCIAS;
	}
	public void setPENDENCIAS(String pENDENCIAS) {
		PENDENCIAS = pENDENCIAS;
	}
	public Integer getID_EXAME() {
		return ID_EXAME;
	}
	public void setID_EXAME(Integer iD_EXAME) {
		ID_EXAME = iD_EXAME;
	}
	public Integer getID_MATRICULA() {
		return ID_MATRICULA;
	}
	public void setID_MATRICULA(Integer iD_MATRICULA) {
		ID_MATRICULA = iD_MATRICULA;
	}
	public Integer getID_REGISTRO() {
		return ID_REGISTRO;
	}
	public void setID_REGISTRO(Integer iD_REGISTRO) {
		ID_REGISTRO = iD_REGISTRO;
	}
	
	
	
	
}
