package dao;

import java.util.List;

import model.Funcao;
import model.Funcionario;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import controller.FuncaoController;
import util.HibernateUtil;

public class FuncaoDaoImp implements FuncaoDao {
	public void save(Funcao funcao) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try{
	SQLQuery query = session.createSQLQuery("insert into Funcao (ID_FUNCAO, NOME, CRITICIDADE, ID_EXAME, DESCRICAO)" +
				" values(:ID_FUNCAO, :NOME, :CRITICIDADE, :ID_EXAME, :DESCRICAO)");
				query.setParameter("ID_FUNCAO", funcao.getID_FUNCAO());
				query.setParameter("NOME", funcao.getNOME());
				query.setParameter("CRITICIDADE", funcao.getCRITICIDADE());
				query.setParameter("ID_EXAME", funcao.getID_EXAME());
				query.setParameter("DESCRICAO", funcao.getDESCRICAO());
				 
				  
			query.executeUpdate();
		}catch(Exception ex){
		       	session.close();
		       	FuncaoController.matriculaErro();
		       	throw ex;
		}
				t.commit();
	}

	
	public List<Funcao> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query  = session.createSQLQuery("select ID_FUNCAO,NOME,CRITICIDADE, f.DESCRICAO , E.NOME_EXAME as EXAMES from FUNCAO as f inner join EXAME as e on e.ID_EXAME = f.ID_EXAME");
		query.addScalar("ID_FUNCAO");
		query.addScalar("NOME");
		query.addScalar("CRITICIDADE");
		query.addScalar("DESCRICAO");
		query.addScalar("EXAMES");
		
		query.setResultTransformer( Transformers.aliasToBean( Funcao.class ) );

	    List<Funcao> users = query.list();

		
		return users;
	}

	public void remove(Funcao funcao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(funcao);
		t.commit();
	}

	public void update(Funcao funcao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("update Funcao set NOME=:NOME, CRITICIDADE =:CRITICIDADE, ID_EXAME = :ID_EXAME, DESCRICAO = :DESCRICAO" +
				" where ID_FUNCAO = :ID_FUNCAO");
						query.setInteger("ID_FUNCAO", funcao.getID_FUNCAO());
						query.setParameter("NOME", funcao.getNOME());
						query.setParameter("CRITICIDADE", funcao.getCRITICIDADE());
						query.setParameter("ID_EXAME", funcao.getID_EXAME());
						query.setParameter("DESCRICAO", funcao.getDESCRICAO());
						
		query.executeUpdate();
		t.commit();
	}

	public List<Funcao> pesquisar(int ID_FUNCAO) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select ID_FUNCAO,NOME,CRITICIDADE, f.DESCRICAO , E.NOME_EXAME as EXAMES from FUNCAO as f inner join EXAME as e on e.ID_EXAME = f.ID_EXAME where ID_FUNCAO= :ID_FUNCAO "); 
		query.setParameter("ID_FUNCAO",ID_FUNCAO );
		query.addScalar("ID_FUNCAO");
		query.addScalar("NOME");
		query.addScalar("CRITICIDADE");
		query.addScalar("DESCRICAO");
		query.addScalar("EXAMES");
		//query.addScalar("CRITICIDADE");
		
		
		query.setResultTransformer( Transformers.aliasToBean( Funcao.class ) );

	    List<Funcao> users = query.list();

		
		return users;

	}
	public List<Funcao> pesquisarNome(String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("FROM Funcao u where u.NOME LIKE :nome"); 
		query.setParameter("nome", "%"+ nome + "%");
		t.commit();
		return query.list();

	}
}
