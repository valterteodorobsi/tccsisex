package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Atestado;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import dao.AtestadoDao;

@ManagedBean(name = "atestadoBen")
@ViewScoped
public class AtestadoController {

	private Atestado atestado;
	private UploadedFile imagem;
	private List<Atestado> listaAtestado = new ArrayList<Atestado>();

	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {

		atestado = new Atestado();

	}

	public Atestado getAtestado() {
		return atestado;
	}

	public void setAtestado(Atestado atestado) {
		this.atestado = atestado;
	}

	public List<Atestado> getListaAtestado() {
		return listaAtestado;
	}

	public void setListaAtestado(List<Atestado> listaAtestado) {
		this.listaAtestado = listaAtestado;
	}

	public UploadedFile getImagem() {
		return imagem;
	}

	public void setImagem(UploadedFile imagem) {
		this.imagem = imagem;
	}

	public void pesquisarAtestadoPorMatricula() {
		Integer matricula = atestado.getMatricula();

		listaAtestado = new AtestadoDao().pesquisar(matricula);

		if (matricula == null || matricula == 0 || listaAtestado == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Nenhum registro foi encontrado."));

		}
	}

	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Atestado Incluso com Sucesso. "));
	}

	public void excluirAtestado(Atestado atestado) {

		new AtestadoDao().remove(atestado);

		pesquisarAtestadoPorMatricula();
		infoExcluir();

	}

	public void infoExcluir() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Atestado  foi excluído com sucesso. "));
	}

	public String adicionarAtestado() {
		AtestadoDao dao = new AtestadoDao();

		atestado.setImagem(imagem.getContents());
		dao.save(atestado);
		info();
		return "/home.jsf";
	}

	// Recebe o arquivo e coloca na variavel imagem para inserir no banco
	public void recebeAtestados(FileUploadEvent event) {

		imagem = event.getFile();

		byte[] arquivoBinario = event.getFile().getContents();

		atestado.setImagem(arquivoBinario);

		FacesMessage msg = new FacesMessage("Sucesso !", event.getFile()
				.getFileName() + " Incluso.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
}
