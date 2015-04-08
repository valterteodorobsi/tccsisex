/**
 * 
 */
package services;

import helper.SimpleEntityManager;

import java.util.List;

import model.User;
import dao.UserDao;

/**
 * SisEx - Sistema de Gerenciamento de Exames
 * 
 * @project SisEx
 * @package br.com.sisex.services
 * @class UserService.java
 * @author Marco Quadros (hanrellz - marco.quadros@mqsistemas.com.br)
 * @version 1.0
 * 
 */
public class UserService {

	private UserDao dao;
	private SimpleEntityManager simpleEntityManager;

	/**
	 * Construtor da Classe UserService.java
	 * @param simpleEntityManager
	 */
	public UserService(SimpleEntityManager simpleEntityManager) {
		this.simpleEntityManager = simpleEntityManager;
		this.dao = new UserDao(this.simpleEntityManager.getEntityManager());
	}
	
	public void save(User user)
	{
		try {
			simpleEntityManager.beginTransaction();
			dao.save(user);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
	}
	
	public User findUserByLoginAndPasswd(String login, String senha)
	{
		try {
		simpleEntityManager.beginTransaction();
		User u = new User();
		u = dao.findUserByLoginAndPasswd(login, senha);
		simpleEntityManager.commit();
		return u;
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
			return new User();
		}
	}
	
	public List<User> findAll()
	{
		return dao.findAll();
	}
	
	private User findByid(Long id)
	{
		return dao.getById(id);
	}

}
