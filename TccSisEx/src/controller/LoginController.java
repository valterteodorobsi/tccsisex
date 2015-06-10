package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.Usuario;
import dao.UsuarioDao;
import dao.UsuarioImpl;


@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginController extends BaseBean {
	private final String DESTINO_SUCESSO = "page2";
	private final String DESTINO_ERRO = null;
	private final String DESTINO_LOGOUT = "index.jsf";
	private final String DESTINO_CADASTRO = "cadastro.jsf?faces-redirect=true";

	private Usuario usuario;
	private String mensagem;
	private UsuarioImpl us = new UsuarioImpl();
	private UsuarioDao dao; 
			
	@PostConstruct
	public void reset() {
		this.usuario = new Usuario();
	}

	public String logar() {

		if (!super.campoPreenchido(this.usuario.getLogin()) && !super.campoPreenchido(this.usuario.getSenha())) {
			this.mensagem = super.MENSAGEM_PRENCHER_CAMPOS;
			return null;
		}

		try {
//			List<Usuario> usuarioLogado = dao.carregarUsuarioLoginSenha(this.usuario);
			List<Usuario> usuarioLogado = us.carregarUsuarioLoginSenha(this.usuario);
			

			if (!usuarioLogado.isEmpty()) {
				this.mensagem = super.NULL;
				this.usuario = usuarioLogado.get(0);
				super.usuarioLogado = this.usuario;
				
//				HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//				response.sendRedirect(DESTINO_SUCESSO);
//				
//				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//				request.getRequestDispatcher(request.getContextPath() + "home.jsf");
				//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				
				//info();
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.jsf");
				return "";
				//return this.DESTINO_SUCESSO;
				//return "/page2?faces-redirect=true";
			} else {
				this.mensagem = super.DADOS_INCORRETOS;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.DESTINO_ERRO;
	}

	public String deslogar() {
		this.usuario = new Usuario();
		super.usuarioLogado = this.usuario;
		return this.DESTINO_LOGOUT;
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
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Colaborador Cadastrado com Sucesso. "));
	}
	
}
