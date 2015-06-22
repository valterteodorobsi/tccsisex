package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Usuario;
import dao.UsuarioImpl;


@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginController extends BaseBean {
	private final String DESTINO_SUCESSO = "page2";
	private final String DESTINO_ERRO = null;
	private final String DESTINO_LOGOUT = "../index.jsf";
	private final String DESTINO_CADASTRO = "cadastro.jsf?faces-redirect=true";

	private Usuario usuario;
	private Usuario usuario2;
	private String mensagem;
	private UsuarioImpl us = new UsuarioImpl();
			
	@PostConstruct
	public void reset() {
		this.usuario = new Usuario();
		this.usuario2 = new Usuario();
	}

	public String logar() {

		if (!super.campoPreenchido(this.usuario.getLogin()) && !super.campoPreenchido(this.usuario.getSenha())) {
			//this.mensagem = super.MENSAGEM_PRENCHER_CAMPOS;
			info();
			return null;
		}

		try {
			List<Usuario> usuarioLogado = us.carregarUsuarioLoginSenha(this.usuario);
			

			if (!usuarioLogado.isEmpty()) {
				this.mensagem = super.NULL;
				this.usuario = usuarioLogado.get(0);
				super.usuarioLogado = this.usuario;
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.jsf");
				return "";
			} else {
				info2();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.DESTINO_ERRO;
	}

	public String deslogar() throws IOException {
		this.usuario = new Usuario();
		super.usuarioLogado = this.usuario;
		FacesContext.getCurrentInstance().getExternalContext().redirect("../index.jsf");
		return "";
	}
	
	public String cadastrar() throws IOException {
		
		UsuarioImpl dao = new UsuarioImpl();
		dao.salvar(usuario2);
		FacesContext.getCurrentInstance().getExternalContext().redirect("../home.jsf");
		return "";
	}
		
		
	
	public Boolean mostrarMensagem() {
		return (this.mensagem != null && !this.mensagem.isEmpty());
	}

	public String redirectCadastro() {
		return this.DESTINO_CADASTRO;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	
	
	public String getDESTINO_SUCESSO() {
		return DESTINO_SUCESSO;
	}

	public String getDESTINO_ERRO() {
		return DESTINO_ERRO;
	}

	public String getDESTINO_LOGOUT() {
		return DESTINO_LOGOUT;
	}

	public String getDESTINO_CADASTRO() {
		return DESTINO_CADASTRO;
	}

	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
						"Favor preencher os campos obrigatorios. "));
	}
	
	public void info2() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
						"Usuario ou senha invalidos. "));
	}

	public Usuario getUsuario2() {
		return usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}
	
	public String alterar() throws IOException {
		
		UsuarioImpl dao = new UsuarioImpl();
		dao.alterar(usuario2);
		FacesContext.getCurrentInstance().getExternalContext().redirect("../index.jsf");
		return "";
	}
	
	public String redirecionar() throws IOException {
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("usuario/usuarioalterar.jsf");
		return "";
	}
	
public String voltar() throws IOException {
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("../index.jsf");
		return "";
	}
}
