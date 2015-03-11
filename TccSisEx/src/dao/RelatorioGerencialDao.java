package dao;

import java.util.List;

import model.Medico;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class RelatorioGerencialDao {
	
	
	
	public List<Medico> pesquisar(String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("FROM Medico u where u.NOME = :nome"); // mondar query para lyke depois
		query.setParameter("nome", nome);
		t.commit();
		return query.list();
	}
}
