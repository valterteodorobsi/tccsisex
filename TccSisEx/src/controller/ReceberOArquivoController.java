package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Arquivo;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import dao.ReceberArquivoDao;

@ManagedBean(name = "arquivoBen")
@ViewScoped
public class ReceberOArquivoController {

	private Arquivo arquivo;
	private UploadedFile imagem;
	private List<Arquivo> listaArquivos = new ArrayList<Arquivo>();

	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {

		arquivo = new Arquivo();

	}

	public List<Arquivo> getListaArquivos() {
		return listaArquivos;
	}

	public void setListaArquivos(List<Arquivo> listaArquivos) {
		this.listaArquivos = listaArquivos;
	}

	public UploadedFile getImagem() {
		return imagem;
	}

	public void setImagem(UploadedFile imagem) {
		this.imagem = imagem;
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public String adicionarArquivo() {
		ReceberArquivoDao dao = new ReceberArquivoDao();

		arquivo.setIMAGEM(imagem.getContents());

		dao.save(arquivo);
		info();
		return "/home.jsf";
	}

	// Recebe o arquivo e coloca na variavel imagem para inserir no banco;
	public void recebeArquivos(FileUploadEvent event) {

		imagem = event.getFile();

		byte[] arquivoBinario = event.getFile().getContents();
		arquivo.setIMAGEM(arquivoBinario);
		FacesMessage msg = new FacesMessage("Sucesso !", event.getFile()
				.getFileName() + "Incluso.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void pesquisarExamePorMatricula() {
		Integer matricula = arquivo.getID_MATRICULA();

		listaArquivos = new ReceberArquivoDao().pesquisar(matricula);
		  
		if (matricula == null || matricula == 0 || listaArquivos == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Nenhum registro foi encontrado."));

		}
	}

	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Exame Incluso com Sucesso. "));
	}

	public void excluirArquivo(Arquivo arquivo) {

		new ReceberArquivoDao().remove(arquivo);

		pesquisarExamePorMatricula();
		infoExcluir();

	}

	public void infoExcluir() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Exame  foi excluído com sucesso. "));
	}

}
