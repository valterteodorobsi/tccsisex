package controller;

import java.io.FileOutputStream;
import java.io.IOException;
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
	private List<Arquivo> listaArquivos = null;

	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {

		arquivo = new Arquivo();

	}

	public List<Arquivo> getListaArquivos() {
		
		if(listaArquivos == null){
			listaArquivos = new ReceberArquivoDao().pesquisarTodos();
			
		}
		
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

	public String adicionarArquivo() throws IOException {
		ReceberArquivoDao dao = new ReceberArquivoDao();

		arquivo.setIMAGEM(imagem.getContents());

		dao.save(arquivo);
		info();
		FacesContext.getCurrentInstance().getExternalContext().redirect("exameincluir.jsf");
		return "";
	}

	// Recebe o arquivo e coloca na variavel imagem para inserir no banco;
	public void recebeArquivos(FileUploadEvent event) throws IOException{

		imagem = event.getFile();

		byte[] arquivoBinario = event.getFile().getContents();
		String caminho = "C:\\imgUP\\exames.png";
		FileOutputStream fos = new FileOutputStream(caminho);
		fos.write(arquivoBinario);
		fos.close();
		arquivo.setIMAGEM(arquivoBinario);
		FacesMessage msg = new FacesMessage("Sucesso !", event.getFile()
				.getFileName() + "Incluso.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	
	 public void criarImagem(Arquivo arquivo) throws IOException {
		 	//String caminho = "C:\\Users\\Hunter\\workspace\\TccSisExames\\WebContent\\resources\\imgUp\\atestado.png";
		String caminho =FacesContext.getCurrentInstance().getExternalContext().getRealPath("")+"/resources/imgUp/exame.png";
		System.out.println(caminho);
		 FileOutputStream fos = new FileOutputStream(caminho);
			fos.write(arquivo.getIMAGEM());
			fos.close();
		 
	 }

	public void pesquisarExamePorMatricula() {
		Integer matricula = arquivo.getID_MATRICULA();

		listaArquivos = new ReceberArquivoDao().pesquisar(matricula);
		  
		if (listaArquivos.isEmpty()) {
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
						"Exame  foi exclu�do com sucesso. "));
	}

}
