/**
 * 
 */
package controller;


import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.User;
import helper.SimpleEntityManager;
import services.UserService;

/**
 * SisEx - Sistema de Gerenciamento de Exames
 * @project	SisEx
 * @package	br.com.sisex.controller
 * @class	UserController.java
 * @author	Marco Quadros (hanrellz - marco.quadros@mqsistemas.com.br) 
 * @version 1.0
 * 
 */
@ManagedBean	
@SessionScoped
public class UserController {

	private UserService service;
	private SimpleEntityManager simpleEntityManager;
	
	private String usuario;
	private String senha;
	private User user;
	
	/**
	 * Construtor da Classe UserController.java
	 * @param
	 */
	public UserController(){}
	
	@PostConstruct
	public void init()
	{
		this.simpleEntityManager = new SimpleEntityManager();
		this.service = new UserService(simpleEntityManager);
	}
	
	
	
	public void saveUser()
	{
		User user = new User();
		user.setLogin("hanrellz");
		user.setPasswd("@12345");
		user.setNivelAcesso(1);
		
		this.service.save(user);
		
	}
	
	public void logar() throws IOException
	{
		try {
			User user = this.service.findUserByLoginAndPasswd(usuario, senha);
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.jsf");
			this.user = user; 
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario ou Senha Invalido", ""));
		}
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

