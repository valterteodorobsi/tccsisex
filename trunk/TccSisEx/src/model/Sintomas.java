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
	private Integer ID_REMEDIO;
	private String NOME_SINTOMAS;
	public String NOME_REMEDIO;
	
	




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
	
	public Integer getID_REMEDIO() {
		return ID_REMEDIO;
	}


	public void setID_REMEDIO(Integer iD_REMEDIO) {
		ID_REMEDIO = iD_REMEDIO;
	}




	public String getNOME_SINTOMAS() {
		return NOME_SINTOMAS;
	}

	public void setNOME_SINTOMAS(String nOME_SINTOMAS) {
		NOME_SINTOMAS = nOME_SINTOMAS;
	}




	public String getNOME_REMEDIO() {
		return NOME_REMEDIO;
	}




	public void setNOME_REMEDIO(String nOME_REMEDIO) {
		NOME_REMEDIO = nOME_REMEDIO;
	}
	
	
	
	
}
