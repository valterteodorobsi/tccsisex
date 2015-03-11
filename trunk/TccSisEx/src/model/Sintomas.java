package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sintomas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer ID_SINTOMAS;
	private String DESCRICAO;
	private String REMEDIO;
	private String NOME_SINTOMAS;
	
	




	public Sintomas(){
		
		
	}
	
	
	
	
	public Integer getID_SINTOMAS() {
		return ID_SINTOMAS;
	}
	public void setID_SINTOMAS(Integer ID_SINTOMAS) {
		this.ID_SINTOMAS = ID_SINTOMAS;
	}
	public String getDESCRICAO() {
		return DESCRICAO;
	}
	public void setDESCRICAO(String DESCRICAO) {
		this.DESCRICAO = DESCRICAO;
	}
	public String getREMEDIO() {
		return REMEDIO;
	}
	public void setREMEDIO(String REMEDIO) {
		this.REMEDIO = REMEDIO;
	}
	public String getNOME_SINTOMAS() {
		return NOME_SINTOMAS;
	}

	public void setNOME_SINTOMAS(String nOME_SINTOMAS) {
		NOME_SINTOMAS = nOME_SINTOMAS;
	}
	
	
	
	
}
