package dao;

import java.util.List;

import model.Exame;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import controller.ExameController;

public class ExameDaoImp implements ExameDao {

	
	public void save(Exame exame)  throws  Exception{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try{
		session.save(exame);
		t.commit();
		}catch (Exception ex) {
			ExameController.matriculaErro();
			session.getTransaction().rollback();
			session.close();
			 throw ex;
		}
	}


	public List<Exame> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Exame where ATIVO = 1").list();
		t.commit();
		return lista;
	}

	public void remove(Exame exame) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("update Exame set ATIVO = 0  WHERE ID_EXAME = :ID_EXAME");
		query.setInteger("ID_EXAME", exame.getID_EXAME());
		query.executeUpdate(); 
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
				.createQuery("FROM Exame u where u.ID_EXAME = :id_exame and ATIVO = 1 "); 
		query.setParameter("id_exame", id_exame);																								// depois
		t.commit();
		return query.list();

	}
	
	public List<Exame> pesquisarNome(String nome_exame) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("FROM Exame u where u.NOME_EXAME LIKE :nome_exame and ATIVO = 1 "); 
		
		query.setParameter("nome_exame", "%"+ nome_exame + "%");
		t.commit();
		return query.list();

	}

}
