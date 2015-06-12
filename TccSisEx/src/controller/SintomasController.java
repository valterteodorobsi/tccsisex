package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Sintomas;

import org.primefaces.event.RowEditEvent;

import dao.SintomasDao;
import dao.SintomasDaoImp;

@ManagedBean(name = "sintomasBen")
@ViewScoped
public class SintomasController {
	private Sintomas sintomas;

	private List<Sintomas> listaSintomas = null;

	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {

		sintomas = new Sintomas();

	}

	public Sintomas getSintomas() {
		return sintomas;
	}

	public void setSintomas(Sintomas sintomas) {
		
		this.sintomas = sintomas;
	}

	public List<Sintomas> getListaSintomas() {
		if(listaSintomas == null){
			listaSintomas = new SintomasDaoImp().list();
		}
		return listaSintomas;
	}

	public void setListaSintomas(List<Sintomas> listaSintomas) {
		this.listaSintomas = listaSintomas;
	}

	public void excluirSintomas(Sintomas sintomas) {

		new SintomasDaoImp().remove(sintomas);

		pesquisarSintomas();

		infoExcluir();
	}

	public String adicionarSintomas() throws IOException {
		SintomasDao dao = new SintomasDaoImp();
		dao.save(sintomas);
		info();
		listaSintomas = null;
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("sintomas.jsf");

		return "";

	}

	public void pesquisarSintomas() {
		String nome = sintomas.getNOME_SINTOMAS();

		listaSintomas = new SintomasDaoImp().pesquisar(nome);
		if (listaSintomas.isEmpty()) {
			warn();

		}

	}

	public List<Sintomas> listaSintomas() {

		listaSintomas = new SintomasDaoImp().list();

		return listaSintomas;

	}

	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Sintomas Cadastrado com Sucesso. "));
	}

	public void infoExcluir() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Sintomas foi excluído com sucesso. "));
	}

	public void warn() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
						"Sintomas registro foi encontrado. "));
	}

	public void infoAlterar() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Sintomas Alterado com Sucesso. "));
	}

	public void onRowEdit(RowEditEvent event) {

		Sintomas sintomas = (Sintomas) event.getObject();

		new SintomasDaoImp().update(sintomas);

		infoAlterar();
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição Cancelada");
		FacesContext.getCurrentInstance().addMessage("Edição Cancelada", msg);
	}

}
