package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Setor;

import org.primefaces.event.RowEditEvent;

import dao.SetorDao;
import dao.SetorDaoImp;

@ManagedBean(name = "setorBen")
@ViewScoped
public class SetorController {

	private Setor setor;

	private List<Setor> listaSetor = null;

	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {

		setor = new Setor();

	}

	public List<Setor> getListaSetor() {
		if (listaSetor == null) {
			listaSetor = new SetorDaoImp().list();
		}

		return listaSetor;
	}

	public void setListaSetor(List<Setor> listaSetor) {
		this.listaSetor = listaSetor;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public void excluirSetor(Setor setor) {

		new SetorDaoImp().remove(setor);

		pesquisarSetor();

		infoExcluir();
	}

	public String adicionarSetor() throws Exception {
		SetorDao dao = new SetorDaoImp();
		dao.save(setor);
		info();
		listaSetor = null;
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("setor.jsf");

		return "";
	}

	public void pesquisarSetor() {
		Integer ID_CENTRO_CUSTO = setor.getID_CENTRO_CUSTO();
		String nome = setor.getNOME();

		if (ID_CENTRO_CUSTO == 0) {
			listaSetor = new SetorDaoImp().pesquisarNome(nome);
			if (listaSetor.isEmpty()) {

				warn();
			}
		} else {

			listaSetor = new SetorDaoImp().pesquisar(ID_CENTRO_CUSTO);
			if (listaSetor.isEmpty()) {
				warn();
			}
		}

	}

	public List<Setor> listaSetor() {
		listaSetor = new SetorDaoImp().list();

		return listaSetor;

	}

	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Setor Cadastrado com Sucesso. "));
	}

	public void infoExcluir() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Setor foi excluído com sucesso. "));
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
						"Setor Alterado com Sucesso. "));
	}

	public void onRowEdit(RowEditEvent event) {

		Setor setor = (Setor) event.getObject();

		new SetorDaoImp().update(setor);

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
						"Centro de Custo já existente. "));
	}

}
