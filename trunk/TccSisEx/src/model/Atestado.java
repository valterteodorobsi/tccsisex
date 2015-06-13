package model;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;



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
	//@Column(name = "NOME_COLABORADOR")  
	private String nomeColaborador;
	@Column(name = "ID_MATRICULA")  
	private Integer ID_MATRICULA;
	//@Column(name = "SETOR")  
	private String setor;
	@Column(name = "CID")  
	private String CID;
	@Column(name = "ID_SETOR")
	private Integer ID_SETOR;
	@Column(name = "DIAS")
	private String DIAS;
	
	
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
	
	public StreamedContent  getImagemProntaHtml() throws UnsupportedEncodingException {
        FacesContext context = FacesContext.getCurrentInstance();
        
		return new DefaultStreamedContent(new ByteArrayInputStream(this.getImagem()), "image/png");
//		return "data:image/png;base64," + new String(getImagem(), "iso-8859-1");
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
		return ID_MATRICULA;
	}
	public void setMatricula(Integer matricula) {
		this.ID_MATRICULA = matricula;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getCid() {
		return CID;
	}
	public void setCid(String cid) {
		this.CID = cid;
	}



	public Integer getID_SETOR() {
		return ID_SETOR;
	}



	public void setID_SETOR(Integer iD_SETOR) {
		ID_SETOR = iD_SETOR;
	}



	public Integer getID_MATRICULA() {
		return ID_MATRICULA;
	}



	public void setID_MATRICULA(Integer iD_MATRICULA) {
		ID_MATRICULA = iD_MATRICULA;
	}



	public String getCID() {
		return CID;
	}



	public void setCID(String cID) {
		CID = cID;
	}



	public String getDIAS() {
		return DIAS;
	}



	public void setDIAS(String dIAS) {
		DIAS = dIAS;
	}

}
