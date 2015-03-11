package dao;

import java.util.List;

import model.Arquivo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class ReceberArquivoDao {
	public void save(Arquivo arquivo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(arquivo);
		t.commit();
	}
	
	
	public List<Arquivo> pesquisar(int matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("FROM Arquivo a where a.MATRICULA = :matricula"); 
		
		query.setParameter("matricula", matricula);																								
		t.commit();
		return query.list();

	}
	
	
	public void remove(Arquivo arquivo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(arquivo);
		t.commit();
	}
}
