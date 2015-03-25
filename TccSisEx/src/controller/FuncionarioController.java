package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Funcionario;
import model.FuncionarioPes;

import org.primefaces.event.RowEditEvent;

import dao.FuncionarioDao;
import dao.FuncionarioDaoImp;

@ManagedBean(name="funcionarioBen")
@ViewScoped
public class FuncionarioController {

	private Funcionario funcionario = new Funcionario();

	private List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
	
	private List<FuncionarioPes> listaFuncionariosPes = new ArrayList<FuncionarioPes>();
	

	public List<FuncionarioPes> getListaFuncionariosPes() {
		return listaFuncionariosPes;
	}

	public void setListaFuncionariosPes(List<FuncionarioPes> listaFuncionariosPes) {
		this.listaFuncionariosPes = listaFuncionariosPes;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void excluirFuncionario(Funcionario funcionario) {

		new FuncionarioDaoImp().remove(funcionario);

		pesquisarFun();
		infoExcluir();
		
	}

	public String adicionarFuncionario() {
		FuncionarioDao dao = new FuncionarioDaoImp();
		dao.save(funcionario);
		info();
		return "/home.jsf";
	}

	public void pesquisarFun() {
		Integer matricula = funcionario.getID_MATRICULA();
		String nome = funcionario.getNOME();
		
		if(matricula == 0){
		
			listaFuncionarios = new FuncionarioDaoImp().pesquisarNome(nome);
		}
		else{
			listaFuncionariosPes = new FuncionarioDaoImp().pesquisar(matricula);
		}
		if (nome == null || matricula == null) {
			warn();

		}

	}
	
	public List<Funcionario> listaColaborador(){

		listaFuncionarios = new FuncionarioDaoImp().list();
		
		return listaFuncionarios;
		
	}
	
	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Colaborador Cadastrado com Sucesso. "));
	}

	public void infoExcluir() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Colaborador foi excluído com sucesso. "));
	}

	public void warn() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
						"Nenhum registro foi encontrado. "));
	}

	public void infoAlterar() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Colaborador Alterado com Sucesso. "));
	}


	public void onRowEdit(RowEditEvent event) {

		Funcionario funcionario = (Funcionario) event.getObject();

		new FuncionarioDaoImp().update(funcionario);
		
		infoAlterar();
		
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição Cancelada");
		FacesContext.getCurrentInstance().addMessage("Edição Cancelada", msg);
	}

}
