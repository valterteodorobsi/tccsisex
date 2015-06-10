package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Exame;

import org.primefaces.event.RowEditEvent;

import dao.ExameDao;
import dao.ExameDaoImp;

@ManagedBean(name = "exameBen")
@ViewScoped
public class ExameController {
	//clase que acesa a view e joga para o dao 
	private Exame exame;

	private List<Exame> listaExame= new ArrayList<Exame>();
	
	//metodo que inicia o objeto 
	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}
	// acessado no init
	private void atribuirEstadoInicial() {

		exame = new Exame();

	}

	public List<Exame> getListaExame() {
		listaExame= new ExameDaoImp().pesquisarVazio();
		return listaExame;
	}

	public void setListaExame(List<Exame> listaExame) {
		this.listaExame = listaExame;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public void excluirExame(Exame exame) {

		new ExameDaoImp().remove(exame);

		pesquisarExame();
		infoExcluir();
		
	}

	public String adicionarExame() throws Exception {
		exame.setATIVO(true);
		ExameDao dao = new ExameDaoImp();
		dao.save(exame);
		info();
		listaExame=null;
		FacesContext.getCurrentInstance().getExternalContext().redirect("../home.jsf");
		
		
		return "";
		
	}
	
	
	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Exame Cadastrado com Sucesso. "));
	}

	public void infoExcluir() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Exame foi excluído com sucesso. "));
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


	public void pesquisarExame() {
		Integer ID_EXAME = exame.getID_EXAME();
		String nome_exame = exame.getNOME_EXAME();

		
		
		if(ID_EXAME == 0){
			
			listaExame = new ExameDaoImp().pesquisarNome(nome_exame);
			if(listaExame.isEmpty()){
				//warn();
			}
		}
		else{
		listaExame = new ExameDaoImp().pesquisar(ID_EXAME);
			if(listaExame.isEmpty()){
			warn();
			}
		}
		
		

	}
	

		
	public List<Exame> listaExame(){
		listaExame= new ExameDaoImp().pesquisarVazio();
		return listaExame;
		
	}
	// evento quando clicado no icone editar na grid chama esse metodo para a alteração do item selecionado.
	public void onRowEdit(RowEditEvent event) {

		Exame exame  = (Exame) event.getObject();

		new ExameDaoImp().update(exame);

		infoAlterar();
	}
	
	// calcela a edição
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição Cancelada");
		FacesContext.getCurrentInstance().addMessage("Edição Cancelada", msg);
	}
	
	public static void matriculaErro() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
						"Código do Exame já existente. "));
	}
}
