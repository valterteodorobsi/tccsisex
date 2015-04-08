/**
 * 
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.User;

/**
 * SisEx - Sistema de Gerenciamento de Exames
 * @project	SisEx
 * @package	br.com.sisex.dao
 * @class	UserDAO.java
 * @author	Ronaldo
 * @version 1.0
 * 
 */
public class UserDao extends GenericDAO<Long, User>{

	/**
	 * Construtor da Classe UserDAO.java
	 * @param entityManager
	 */
	public UserDao(EntityManager entityManager) {
		super(entityManager);
	}
	
	public User findUserByLoginAndPasswd(String login, String senha)
	{
		Query query;
		EntityManager entityManager = null;
		query = entityManager.createQuery("FROM User u where u.login = :login and u.passwd = :passwd");
		query.setParameter("login", login);
		query.setParameter("passwd", senha);
		return (User) query.getSingleResult();
	}

}
