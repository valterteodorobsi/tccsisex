package dao;

import java.util.List;

import model.Sintomas;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class SintomasDaoImp implements SintomasDao {

	
	
	public void save(Sintomas sintomas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(sintomas);
		t.commit();
	}

	public void remove(Sintomas sintomas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(sintomas);
		t.commit();
	}

	public void update(Sintomas sintomas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(sintomas);

		t.commit();
	}

	public List<Sintomas> pesquisar(String NOME_SINTOMAS) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("FROM Sintomas s where s.NOME_SINTOMAS LIKE :NOME_SINTOMAS"); 
																										
		query.setParameter("NOME_SINTOMAS","%"+ NOME_SINTOMAS + "%" );

		return query.list();
	}

	
	public List<Sintomas> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Sintomas").list();
		t.commit();
		return lista;
	}
}
