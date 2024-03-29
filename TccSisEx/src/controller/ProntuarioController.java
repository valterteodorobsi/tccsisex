package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.Prontuario;

import org.hibernate.HibernateException;
import org.primefaces.event.RowEditEvent;

import dao.ProntuarioDao;
import dao.ProntuarioDaoImp;


@ManagedBean(name = "prontuarioBen")
@RequestScoped
public class ProntuarioController {

	
	private Prontuario prontuario;
	
	private List<Prontuario> listaProntuario = null;
	

	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {
		prontuario = new Prontuario();

	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public List<Prontuario> getListaProntuario() {
		
		if(listaProntuario == null){
			listaProntuario = new ProntuarioDaoImp().pesquisarNome();
			
		}
		return listaProntuario;
	}

	public void setListaProntuario(List<Prontuario> listaProntuario) {
		this.listaProntuario = listaProntuario;
	}
	
	public void excluirProntuario(Prontuario prontuario) {

		new ProntuarioDaoImp().remove(prontuario);

		listaProntuario = null;
		FacesMessage msg = new FacesMessage("Prontu�rio foi exclu�do com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	
	public String adicionarProntuario() throws HibernateException, Exception {
		ProntuarioDao dao = new ProntuarioDaoImp();
		dao.save(prontuario);
		FacesMessage msg = new FacesMessage(
				"Prontu�rio Cadastrado com Sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext().redirect("prontuario.jsf");
		return "";
	}
	

	public void pesquisarProntuarioMatricula() {
		Integer id_matricula = prontuario.getid_matricula();
		
		
		
		listaProntuario = new ProntuarioDaoImp().pesquisarPorMatricula(id_matricula); 
		
		if (listaProntuario.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Nenhum registro foi encontrado."));

		}
	}
		
	
	
	public List<Prontuario> listaProntuario(){
		listaProntuario= new ProntuarioDaoImp().list();
		
		return listaProntuario;
		
	}

	
	public void onRowEdit(RowEditEvent event) {

		Prontuario prontuario  = (Prontuario) event.getObject();

		new ProntuarioDaoImp().update(prontuario);

		FacesMessage msg = new FacesMessage("Prontu�rio Alterado com Sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edi��o Cancelada");
		FacesContext.getCurrentInstance().addMessage("Edi��o Cancelada", msg);
	}



	public static void matriculaErro() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
						"Matricula j� existente. "));
	}
	
	
}
