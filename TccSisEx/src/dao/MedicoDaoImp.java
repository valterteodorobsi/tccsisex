package dao;

import java.util.List;

import model.Medico;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
			
			 throw ex;
		}
		session.close();
	}


	public List<Medico> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Medico u where u.ATIVO = 1").list();
		t.commit();
		return lista;
	}

	public void remove(Medico Medico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("update Medico set ATIVO = 0  WHERE ID_MATRICULA = :ID_MATRICULA");
		query.setInteger("ID_MATRICULA", Medico.getID_MATRICULA());
		query.executeUpdate(); 
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
		Query query = session.createQuery("FROM Medico u where u.ID_MATRICULA = :matricula and ATIVO = 1"); 
		query.setParameter("matricula", matricula);
		t.commit();
		return query.list();
		
	}
	public List<Medico> pesquisarNome( String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("FROM Medico u where  u.NOME LIKE :nome and ATIVO = 1"); 
		query.setParameter("nome", "%"+ nome + "%");
		t.commit();
		return query.list();
		
	}
	
}