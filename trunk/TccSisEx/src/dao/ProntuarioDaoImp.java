package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.FuncionarioController;
import util.HibernateUtil;
import model.Exame;
import model.Funcao;
import model.Funcionario;
import model.Prontuario;

public class ProntuarioDaoImp implements ProntuarioDao {

	public void save(Prontuario prontuario)  throws  Exception{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try{
			SQLQuery query = session.createSQLQuery("insert into Prontuario (ID_MATRICULA, ID_EXAME, ID_PRONTUARIO, ID_REGISTRO,  LINK_IMAGENS, PENDENCIAS)" +
			" values(:ID_MATRICULA, :ID_EXAME, :ID_PRONTUARIO, :ID_REGISTRO, :LINK_IMAGENS, :PENDENCIAS)");
			query.setParameter("ID_MATRICULA", prontuario.getID_MATRICULA());
			query.setParameter("ID_EXAME", prontuario.getID_EXAME());
			query.setParameter("ID_PRONTUARIO", prontuario.getID_PRONTUARIO());
			query.setParameter("ID_REGISTRO", prontuario.getID_REGISTRO());
			query.setParameter("LINK_IMAGENS", prontuario.getLINK_IMAGENS());
			query.setParameter("PENDENCIAS", prontuario.getPENDENCIAS());
			  
		query.executeUpdate();
		
			}catch(Exception ex){
				  
				//session.refresh(funcionario);
			       	session.close();
			       	//FuncionarioController.matriculaErro();
			        throw ex;
				 
			}
			t.commit();	}


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
		SQLQuery query = session.createSQLQuery("update Prontuario set ID_MATRICULA =:ID_MATRICULA, ID_EXAME = :ID_EXAME,ID_REGISTRO = :ID_REGISTRO,LINK_IMAGENS = :LINK_IMAGENS, PENDENCIAS= :PENDENCIAS where ID_PRONTUARIO = :ID_PRONTUARIO");
				query.setInteger("ID_MATRICULA", prontuario.getID_MATRICULA());
				query.setParameter("ID_EXAME", prontuario.getID_EXAME());
				query.setParameter("ID_PRONTUARIO", prontuario.getID_PRONTUARIO());
				query.setParameter("ID_REGISTRO", prontuario.getID_REGISTRO());
				query.setParameter("LINK_IMAGENS", prontuario.getLINK_IMAGENS());
				query.setParameter("PENDENCIAS", prontuario.getPENDENCIAS());
				  
			query.executeUpdate();

		t.commit();

	}

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


	@Override
	public List<Prontuario> pesquisarNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}
}
