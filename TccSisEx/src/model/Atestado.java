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
@Table(name="ANEXO_ATESTADO")
public class Atestado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_ATESTADO") 
	private Integer idAtestado;
	@Lob  
	@Column(name = "IMAGEM")  
	private byte [] imagem;
	@Column(name = "NOME_COLABORADOR")  
	private String nomeColaborador;
	@Column(name = "MATRICULA")  
	private Integer matricula;
	@Column(name = "SETOR")  
	private String setor;
	@Column(name = "CID")  
	private String cid;
	
	
    public Atestado() {

    }
		
	
	
	public Integer getIdAtestado() {
		return idAtestado;
	}
	public void setIdAtestado(Integer idAtestado) {
		this.idAtestado = idAtestado;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public String getNomeColaborador() {
		return nomeColaborador;
	}
	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	

	
}
