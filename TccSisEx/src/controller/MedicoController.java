package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Medico;

import org.hibernate.HibernateException;
import org.primefaces.event.RowEditEvent;

import dao.FuncionarioDaoImp;
import dao.MedicoDao;
import dao.MedicoDaoImp;

@ManagedBean(name = "medicoBen")
@ViewScoped
public class MedicoController {

	private Medico medico;

	private List<Medico> listaMedicos =  null ;

	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {

		medico = new Medico();
		

	}

	public List<Medico> getListaMedicos() {
		
		if(listaMedicos == null){
			listaMedicos = new MedicoDaoImp().list();
		}
		return listaMedicos;
	}

	public void setListaMedicos(List<Medico> listaMedicos) {
		this.listaMedicos = listaMedicos;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public void excluirMedico(Medico medico) {

		new MedicoDaoImp().remove(medico);

		pesquisarMedico();
		infoExcluir();

		
	}

	public String adicionarMedico()  throws HibernateException, Exception {
		medico.setATIVO(true);
		MedicoDao dao = new MedicoDaoImp();
		dao.save(medico);
		info();
		listaMedicos = null;
		FacesContext.getCurrentInstance().getExternalContext().redirect("medico.jsf");
		return "";
	}

	public void pesquisarMedico() {
		Integer matricula = medico.getID_MATRICULA();
		String nome = medico.getNOME();

		if(matricula == 0){
			
			listaMedicos = new MedicoDaoImp().pesquisarNome(nome);
			if(listaMedicos.isEmpty()){
				warn();
			}
		}
		else{
		listaMedicos = new MedicoDaoImp().pesquisar(matricula);
			if(listaMedicos.isEmpty()){
			warn();
			}
		}
		
		}

	
	
	public List<Medico> listaMedico() {

		listaMedicos = new MedicoDaoImp().list();

		return listaMedicos;

	}
	
	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"M�dico Cadastrado com Sucesso. "));
	}

	public void infoExcluir() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"M�dico foi exclu�do com sucesso. "));
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
						"M�dico Alterado com Sucesso. "));
	}
	public void onRowEdit(RowEditEvent event) {

		Medico medico = (Medico) event.getObject();

		new MedicoDaoImp().update(medico);

		infoAlterar();
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
