package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Setor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer ID_CENTRO_CUSTO;
	private String NOME;
	private String GERENTE_RESPON;
	private Integer QUANT_APROX_COLABORADORES;

	public Setor() {
	}

	public Setor(Integer ID_CENTRO_CUSTO, String NOME, String GERENTE_RESPON,
			Integer QUANT_APROX_COLABORADORES) {

		this.ID_CENTRO_CUSTO = ID_CENTRO_CUSTO;
		this.NOME = NOME;
		this.GERENTE_RESPON = GERENTE_RESPON;
		this.QUANT_APROX_COLABORADORES = QUANT_APROX_COLABORADORES;
	}

	public Integer getID_CENTRO_CUSTO() {
		return ID_CENTRO_CUSTO;
	}

	public void setID_CENTRO_CUSTO(Integer iD_CENTRO_CUSTO) {
		ID_CENTRO_CUSTO = iD_CENTRO_CUSTO;
	}

	public String getNOME() {
		return NOME;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public String getGERENTE_RESPON() {
		return GERENTE_RESPON;
	}

	public void setGERENTE_RESPON(String gERENTE_RESPON) {
		GERENTE_RESPON = gERENTE_RESPON;
	}

	public Integer getQUANT_APROX_COLABORADORES() {
		return QUANT_APROX_COLABORADORES;
	}

	public void setQUANT_APROX_COLABORADORES(Integer qUANT_APROX_COLABORADORES) {
		QUANT_APROX_COLABORADORES = qUANT_APROX_COLABORADORES;
	}

}