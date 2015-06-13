package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import model.Atestado;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import dao.AtestadoDao;

@ManagedBean(name = "atestadoBen")
@ViewScoped
public class AtestadoController {

	private Atestado atestado;
	private UploadedFile imagem;
	private List<Atestado> listaAtestado = null;

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
		if(listaAtestado == null){
			listaAtestado = new AtestadoDao().pesquisarTodos();
			
		}
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
		
		if(matricula != 0 ){
		listaAtestado = new AtestadoDao().pesquisar(matricula);
		//listaAtestado.get(0).getImagem();
		
		
			if (listaAtestado.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Nenhum registro foi encontrado."));

			}
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

	public String adicionarAtestado() throws IOException {
		AtestadoDao dao = new AtestadoDao();

		atestado.setImagem(imagem.getContents());
		dao.save(atestado);
		info();
		FacesContext.getCurrentInstance().getExternalContext().redirect("atestados.jsf");
		return "";
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
	
		
		/**
		 * <p>
	     * 	Gera uma imagem apartir de um array de <code>bytes</code>.
	     * </p>
		 * @param byteArray <code>byte[]</code>
		 * @throws IOException
		 * @return bImageFromConvert {@link BufferedImage}
		 */
		public static BufferedImage converterByteArrayParaImg(final byte[] byteArray) throws IOException, IllegalArgumentException {
			if(byteArray == null) {
				throw new IllegalArgumentException();
			}
			InputStream in = new ByteArrayInputStream(byteArray);
			BufferedImage bImageFromConvert = ImageIO.read(in);
			return bImageFromConvert;
		}
		/**
		 * <p>
	     * 	A partir da imagem recuperada através do caminho passado, é gerado um array de <code>bytes</code>.
	     * </p>
		 * @param caminho {@link String}.
		 * @param extensaoImg {@link String}.
		 * @return <code>byte[]</code>.
		 * @throws IOException
		 */
		public static byte[] converterImgParaByteArray(final String caminho, final String extensaoImg) throws IOException, IllegalArgumentException{
			
			if(caminho == null) {
				throw new IllegalArgumentException();
			}
			
			byte[] imageInByte;
			BufferedImage originalImage = ImageIO.read(new File(caminho));
		
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, extensaoImg, baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
			return imageInByte;
		}


}


