/**
 * 
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SisEx - Sistema de Gerenciamento de Exames
 * 
 * @project TCCSisEx
 * @package model
 * @class User.java
 * @version 1.0
 * 
 */
@Entity
@Table(name = "USUARIO")
public class User {

	@Id
	@Column(name = "ID_USUARIO", nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "LOGIN", nullable=false)
	private String login;

	@Column(name = "SENHA", nullable=false)
	private String passwd;

	
	@Column(name = "NIVEL_USUARIO", nullable=false)
	private int nivelAcesso;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd
	 *            the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return nivelAcesso
	 */
	public int getNivelAcesso() {
		return nivelAcesso;
	}

	/**
	 * @param nivelAcesso
	 *            the nivelAcesso to set
	 */
	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
}
