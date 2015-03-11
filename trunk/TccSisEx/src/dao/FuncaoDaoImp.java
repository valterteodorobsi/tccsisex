package dao;

import java.util.List;

import model.Funcao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class FuncaoDaoImp implements FuncaoDao {
	public void save(Funcao funcao) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(funcao);
		t.commit();
	}

	
	public List<Funcao> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Funcao ").list();
		t.commit();
		return lista;
	}

	public void remove(Funcao funcao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(funcao);
		t.commit();
	}

	public void update(Funcao funcao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(funcao);

		t.commit();
	}

	public List<Funcao> pesquisar(int ID_FUNCAO) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("FROM Funcao u where u.ID_FUNCAO = :id_funcao"); 
		query.setParameter("id_funcao", ID_FUNCAO);																								// depois
		t.commit();
		return query.list();

	}
	public List<Funcao> pesquisarNome(String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("FROM Funcao u where u.NOME LIKE :nome"); 
		query.setParameter("nome", "%"+ nome + "%");
		t.commit();
		return query.list();

	}
}
