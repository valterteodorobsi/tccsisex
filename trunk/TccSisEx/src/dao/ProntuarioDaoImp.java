package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import model.Exame;
import model.Funcao;
import model.Funcionario;
import model.Prontuario;

public class ProntuarioDaoImp implements ProntuarioDao {

	public void save(Prontuario prontuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(prontuario);
		t.commit();
	}

	public Prontuario getExame(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Prontuario) session.load(Prontuario.class, id);
	}

	public List<Prontuario> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Prontuario").list();
		t.commit();
		return lista;
	}

	public void remove(Prontuario prontuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(prontuario);
		t.commit();
	}

	public void update(Prontuario prontuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(prontuario);

		t.commit();

	}

/*	public List<Prontuario> pesquisarPorMatriculaOuNome(Integer id_matricula, String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("FROM Exame u where u.ID_EXAME = :id_exame OR u.NOME_EXAME = :nome_exame"); 
		query.setParameter("id_matricula", id_matricula);																								
		query.setParameter("nome", nome);
		t.commit();
		return query.list();
	}*/
	public List<Prontuario> pesquisarPorMatricula(Integer id_matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("from Prontuario p where p.ID_MATRICULA = :id_matricula"); 
		query.setParameter("id_matricula", id_matricula);
		
		t.commit();
		return query.list();
	}
	public List<Funcionario> pesquisarPorMatriculaFuncionario(Integer id_matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("from Funcionario f where f.ID_MATRICULA = :id_matricula");
				query.setParameter("id_matricula",id_matricula);
		t.commit();
		return query.list();
	}
}
