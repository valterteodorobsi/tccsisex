package dao;

import java.util.List;

import model.Funcionario;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class FuncionarioDaoImp implements FuncionarioDao {

	public void save(Funcionario funcionario) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(funcionario);
		t.commit();
	}


	public List<Funcionario> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Funcionario").list();
		t.commit();
		return lista;
	}

	public void remove(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(funcionario);
		t.commit();
	}

	public void update(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(funcionario);
		
		t.commit();
	}
	public List<Funcionario> pesquisar(Integer matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("FROM Funcionario u where u.ID_MATRICULA = :matricula "); 
		query.setParameter("matricula", matricula);
		
		t.commit();
		return query.list();
		
	}
	
	public List<Funcionario> pesquisarNome(String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("FROM Funcionario u where u.NOME LIKE :nome"); 
		query.setParameter("nome", "%"+ nome + "%");
		
		t.commit();
		return query.list();
		
	}
}