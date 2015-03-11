package dao;

import java.util.List;

import model.Remedio;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class RemedioDao {
	
	
	public List<Remedio> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Remedio").list();
		t.commit();
		return lista;
	}
}
