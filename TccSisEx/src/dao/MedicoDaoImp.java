package dao;

import java.util.List;

import model.Medico;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import controller.MedicoController;

public class MedicoDaoImp implements MedicoDao  {

	public void save(Medico Medico) throws  Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try{
		session.save(Medico);
		t.commit();
		}catch (Exception ex) {
			MedicoController.matriculaErro();
			session.getTransaction().rollback();
			session.close();
			 throw ex;
		}
		
	}


	public List<Medico> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Medico").list();
		t.commit();
		return lista;
	}

	public void remove(Medico Medico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(Medico);
		t.commit();
	}

	public void update(Medico Medico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(Medico);
		
		t.commit();
	}
	public List<Medico> pesquisar(int matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("FROM Medico u where u.ID_MATRICULA = :matricula "); 
		query.setParameter("matricula", matricula);
		t.commit();
		return query.list();
		
	}
	public List<Medico> pesquisarNome( String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("FROM Medico u where  u.NOME LIKE :nome"); 
		query.setParameter("nome", "%"+ nome + "%");
		t.commit();
		return query.list();
		
	}
	
}