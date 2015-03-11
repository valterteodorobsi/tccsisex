package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Remedio implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer ID_REMEDIO;
	private String NOME;
	
	
	

	public Remedio(){
		
		
	}
	
	public Integer getID_REMEDIO() {
		return ID_REMEDIO;
	}


	public void setID_REMEDIO(Integer iD_REMEDIO) {
		ID_REMEDIO = iD_REMEDIO;
	}


	public String getNOME() {
		return NOME;
	}


	public void setNOME(String nOME) {
		NOME = nOME;
	}

}
