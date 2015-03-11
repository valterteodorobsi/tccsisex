package dao;

import java.util.List;

import model.Exame;
import model.Funcao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class ExameDaoImp implements ExameDao {

	
	public void save(Exame exame) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(exame);
		t.commit();
	}


	public List<Exame> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Exame").list();
		t.commit();
		return lista;
	}

	public void remove(Exame exame) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(exame);
		t.commit();
	}

	public void update(Exame exame) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(exame);

		t.commit();

	}

	public List<Exame> pesquisar(Integer id_exame) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("FROM Exame u where u.ID_EXAME = :id_exame "); 
		query.setParameter("id_exame", id_exame);																								// depois
		t.commit();
		return query.list();

	}
	
	public List<Exame> pesquisarNome(String nome_exame) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("FROM Exame u where u.NOME_EXAME LIKE :nome_exame"); 
		
		query.setParameter("nome_exame", "%"+ nome_exame + "%");
		t.commit();
		return query.list();

	}

}
