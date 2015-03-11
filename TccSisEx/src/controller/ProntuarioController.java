package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.Funcionario;
import model.Prontuario;

import org.primefaces.event.RowEditEvent;

import dao.ProntuarioDao;
import dao.ProntuarioDaoImp;


@ManagedBean(name = "prontuarioBen")
@RequestScoped
public class ProntuarioController {

	
	private Funcionario funcionario;
	private Prontuario prontuario;
	
	private List<Prontuario> listaProntuario = new ArrayList<Prontuario>();
	private List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
	
	public List<Funcionario> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {
		setFuncionario(new Funcionario());
		prontuario = new Prontuario();

	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public List<Prontuario> getListaProntuario() {
		return listaProntuario;
	}

	public void setListaProntuario(List<Prontuario> listaProntuario) {
		this.listaProntuario = listaProntuario;
	}
	
	public void excluirProntuario(Prontuario prontuario) {

		new ProntuarioDaoImp().remove(prontuario);

		pesquisarProntuarioMatricula();

		FacesMessage msg = new FacesMessage("Prontuário foi excluído com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	
	public String adicionarProntuario() {
		ProntuarioDao dao = new ProntuarioDaoImp();
		dao.save(prontuario);
		FacesMessage msg = new FacesMessage(
				"Prontuário Cadastrado com Sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "/home.jsf";
	}
	
/*	public void pesquisarProntuario() {
		Integer ID_MATRICULA = prontuario.getID_MATRICULA();
		String nome = funcionario.getNOME();
		System.out.println("chegou");
		listaProntuario = new ProntuarioDaoImp().pesquisarPorMatriculaOuNome(ID_MATRICULA, nome);
		if (nome == null || ID_MATRICULA == 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Nenhum registro foi encontrado."));

		}
	}*/

	public void pesquisarProntuarioMatricula() {
		Integer ID_MATRICULA = prontuario.getID_MATRICULA();
		
		
		
		listaProntuario = new ProntuarioDaoImp().pesquisarPorMatricula(ID_MATRICULA); 
		listaFuncionario = new ProntuarioDaoImp().pesquisarPorMatriculaFuncionario(ID_MATRICULA);
		// ver como fazer
		//funcionario.equals(listaProntuario.contains(funcionario));
		//System.out.println(listaProntuario);
		
		if (ID_MATRICULA == null || ID_MATRICULA == 0 || listaProntuario == null) {
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

		FacesMessage msg = new FacesMessage("Prontuário Alterado com Sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição Cancelada");
		FacesContext.getCurrentInstance().addMessage("Edição Cancelada", msg);
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	
	
}
