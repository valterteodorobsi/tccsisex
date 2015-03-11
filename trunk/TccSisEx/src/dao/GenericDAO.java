/**
 * 
 */
package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * SisEx - Sistema de Gerenciamento de Exames
 * 
 * @project SisEx
 * @package br.com.sisex.dao
 * @class GenericDAO.java
 * @author Marco Quadros (hanrellz - marco.quadros@mqsistemas.com.br)
 * @version 1.0
 * 
 */

@SuppressWarnings("unchecked")
public class GenericDAO<PrimaryKey, TClass> {

	public EntityManager entityManager;

	/**
	 * Construtor da Classe GenericDAO.java
	 * @param entityManager
	 */
	public GenericDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	public TClass getById(PrimaryKey pk) {
		return (TClass) entityManager.find(getTypeClass(), pk);
	}

	public void save(TClass entity) {
		entityManager.persist(entity);
	}

	public void update(TClass entity) {
		entityManager.merge(entity);
	}

	public void delete(TClass entity) {
		entityManager.remove(entity);
	}

	public List<TClass> findAll() {
		return entityManager.createQuery(("FROM " + getTypeClass().getName()))
				.getResultList();
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
		return clazz;
	}

}
