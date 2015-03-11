package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Medico;

import org.primefaces.event.RowEditEvent;

import dao.MedicoDao;
import dao.MedicoDaoImp;

@ManagedBean(name = "medicoBen")
@ViewScoped
public class MedicoController {

	private Medico medico;

	private List<Medico> listaMedicos = new ArrayList<Medico>();

	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {

		medico = new Medico();
		

	}

	public List<Medico> getListaMedicos() {
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

	public String adicionarMedico() {
		MedicoDao dao = new MedicoDaoImp();
		dao.save(medico);
		info();
		return "/home.jsf";
	}

	public void pesquisarMedico() {
		Integer matricula = medico.getID_MATRICULA();
		String nome = medico.getNOME();

		if(matricula == 0){
			
			listaMedicos = new MedicoDaoImp().pesquisarNome(nome);
		}
		else{
		listaMedicos = new MedicoDaoImp().pesquisar(matricula);
		}
		if (nome == null || matricula == null) {
			warn();

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
						"Médico Cadastrado com Sucesso. "));
	}

	public void infoExcluir() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Médico foi excluído com sucesso. "));
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
						"Médico Alterado com Sucesso. "));
	}
	public void onRowEdit(RowEditEvent event) {

		Medico medico = (Medico) event.getObject();

		new MedicoDaoImp().update(medico);

		infoAlterar();
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição Cancelada");
		FacesContext.getCurrentInstance().addMessage("Edição Cancelada", msg);
	}

}
