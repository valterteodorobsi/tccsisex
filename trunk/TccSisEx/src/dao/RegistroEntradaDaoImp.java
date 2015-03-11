package dao;

import java.util.List;

import model.RegistroEntrada;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class RegistroEntradaDaoImp implements RegistroEntradaDao {


	public void save(RegistroEntrada registro) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			session.save(registro);
			t.commit();
		
	}
	
	
	public List<RegistroEntrada> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Registro_entrada").list();
		t.commit();
		return lista;
	}
	public List<RegistroEntrada> pesquisar(Integer ID_MATRICULA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("FROM RegistroEntrada r where r.ID_MATRICULA = :ID_MATRICULA and r.DATA_SAIDA is null");
																									
																										
		query.setParameter("ID_MATRICULA", ID_MATRICULA);																								// depois
		t.commit();
		return query.list();

	}
	
	
	public void update(Integer id_matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		/*session.update(registro);
		t.commit();*/
		Query query = session
				.createSQLQuery("UPDATE REGISTRO_ENTRADA SET DATA_SAIDA = GETDATE() WHERE ID_MATRICULA = :id_matricula");
		
		query.setParameter("id_matricula", id_matricula);																								// depois
		query.executeUpdate();
		t.commit();
	}
}
