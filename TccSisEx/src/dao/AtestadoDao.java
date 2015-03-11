package dao;

import java.util.List;

import model.Atestado;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class AtestadoDao {

	
	public void save(Atestado atestado) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(atestado);
		t.commit();
	}
	
	
	public List<Atestado> pesquisar(int matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("FROM Atestado a where a.matricula = :matricula"); 
		
		query.setParameter("matricula", matricula);																								
		t.commit();
		return query.list();

	}
	
	
	public void remove(Atestado atestado) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(atestado);
		t.commit();
	}
}
