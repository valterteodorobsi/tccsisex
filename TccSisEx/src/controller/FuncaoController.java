package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Funcao;

import org.primefaces.event.RowEditEvent;

import dao.FuncaoDao;
import dao.FuncaoDaoImp;

@ManagedBean(name = "funcaoBen")
@ViewScoped
public class FuncaoController {

	private Funcao funcao;

	private List<Funcao> listaFuncao = new ArrayList<Funcao>();

	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {

		funcao = new Funcao();

	}

	public List<Funcao> getListaFuncao() {
		return listaFuncao;
	}

	public void setListaFuncaos(List<Funcao> listaFuncao) {
		this.listaFuncao = listaFuncao;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public void excluirFuncao(Funcao funcao) {

		new FuncaoDaoImp().remove(funcao);

		pesquisarFuncao();
		infoExcluir();

		
	}

	public String adicionarFuncao() throws Exception {
		FuncaoDao dao = new FuncaoDaoImp();
		dao.save(funcao);
		info();
		
		return "/home.jsf";
	}

	public void pesquisarFuncao() {
		Integer ID_FUNCAO = funcao.getID_FUNCAO();
		String nome = funcao.getNOME();

		
	
		if(ID_FUNCAO == 0){
			
			listaFuncao = new FuncaoDaoImp().pesquisarNome(nome);
		}
		else{
		listaFuncao = new FuncaoDaoImp().pesquisar(ID_FUNCAO);
		}
		if (nome == null || ID_FUNCAO == null) {
			warn();

		}

	}

	public List<Funcao> listaFuncao() {

		listaFuncao = new FuncaoDaoImp().list();

		return listaFuncao;

	}

	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Função Cadastrada com Sucesso. "));
	}

	public void infoExcluir() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Função foi excluída com sucesso. "));
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
						"Função Alterada com Sucesso. "));
	}

	public void onRowEdit(RowEditEvent event) {

		Funcao funcao = (Funcao) event.getObject();

		new FuncaoDaoImp().update(funcao);

		infoAlterar();
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição Cancelada");
		FacesContext.getCurrentInstance().addMessage("Edição Cancelada", msg);
	}

	
	public static void matriculaErro() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
						"Código da Função já existente. "));
	}
	
}
