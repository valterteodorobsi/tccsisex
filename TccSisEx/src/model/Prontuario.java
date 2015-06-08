package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Prontuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_PRONTUARIO") 
	private Integer ID_PRONTUARIO;
	private String PENDENCIAS;
	@Column(name = "ID_MATRICULA") 
	private Integer id_matricula;
	
	private String nomeFuncionario;
	private String nomeFuncao;
	private String nomeSetor;
	private Date data_nasc;
	private String email;
	private String rg;
	private Integer ramal;
	
	

	public Prontuario(){
			
	}
	
	
	public Integer getID_PRONTUARIO() {
		return ID_PRONTUARIO;
	}
	public void setID_PRONTUARIO(Integer iD_PRONTUARIO) {
		ID_PRONTUARIO = iD_PRONTUARIO;
	}
	public String getPENDENCIAS() {
		return PENDENCIAS;
	}
	public void setPENDENCIAS(String pENDENCIAS) {
		PENDENCIAS = pENDENCIAS;
	}
	public Integer getid_matricula() {
		return id_matricula;
	}
	public void setid_matricula(Integer id_matricula) {
		this.id_matricula = id_matricula;
	}


	public String getNomeFuncionario() {
		return nomeFuncionario;
	}


	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}


	public String getNomeFuncao() {
		return nomeFuncao;
	}


	public void setNomeFuncao(String nomeFuncao) {
		this.nomeFuncao = nomeFuncao;
	}


	public String getNomeSetor() {
		return nomeSetor;
	}


	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}


	public Date getData_nasc() {
		return data_nasc;
	}


	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public Integer getRamal() {
		return ramal;
	}


	public void setRamal(Integer ramal) {
		this.ramal = ramal;
	}
	
	
	
	
}
