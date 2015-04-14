package controller;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import model.User;

@ManagedBean(name = "userController")
public class UserController {
	private User usuario = new User();
	 
	  public String doEfetuarLogin() {
	    if("sakurai".equals(usuario.getUsername())
	            && "123".equals(usuario.getPassword())) {
	      return"home.jsf";
	    } else {
	      /* Cria uma mensagem. */
	      FacesMessage msg = new FacesMessage("Usuário ou senha inválido!");
	      /* Obtém a instancia atual do FacesContext e adiciona a mensagem de erro nele. */
	      FacesContext.getCurrentInstance().addMessage("erro", msg);
	      return null;
	    }
	  }
	 
	  public User getUsuario() {
	    return usuario;
	  }
	 
	  public void setUsuario(User usuario) {
	    this.usuario = usuario;
	  }
	  
	  
	  
}
