package dao;

import java.util.List;

import model.Setor;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class SetorDaoImp implements SetorDao {
	public void save(Setor setor) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(setor);
		t.commit();
	}


	public List<Setor> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Setor ").list();
		t.commit();
		return lista;
	}

	public void remove(Setor setor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(setor);
		t.commit();
	}

	public void update(Setor setor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(setor);

		t.commit();
	}

	public List<Setor> pesquisar(Integer ID_CENTRO_CUSTO) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("FROM Setor u where u.ID_CENTRO_CUSTO = :id_centro_custo"); 
		query.setParameter("id_centro_custo", ID_CENTRO_CUSTO);																								// depois
		t.commit();
		return query.list();

	}
	
	public List<Setor> pesquisarNome(String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("FROM Setor u where  u.NOME LIKE :nome"); 
		query.setParameter("nome", "%"+ nome + "%");
		t.commit();
		return query.list();

	}
}
