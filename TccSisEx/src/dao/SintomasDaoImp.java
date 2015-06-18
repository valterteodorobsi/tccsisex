package dao;

import java.util.List;

import model.Funcionario;
import model.Sintomas;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class SintomasDaoImp implements SintomasDao {

	
	
	public void save(Sintomas sintomas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery("insert into Sintomas (NOME_SINTOMAS, DESCRICAO, ID_REMEDIO)" +
				" values( :NOME_SINTOMAS, :DESCRICAO, :ID_REMEDIO )");
				query.setParameter("NOME_SINTOMAS", sintomas.getNOME_SINTOMAS());
				query.setParameter("DESCRICAO", sintomas.getDESCRICAO());
				query.setParameter("ID_REMEDIO", sintomas.getID_REMEDIO());
				 
				  
			query.executeUpdate();
				t.commit();
	}

	public void remove(Sintomas sintomas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(sintomas);
		t.commit();
	}

	public void update(Sintomas sintomas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("update Sintomas set NOME_SINTOMAS=:NOME_SINTOMAS, DESCRICAO =:DESCRICAO, ID_REMEDIO = :ID_REMEDIO"
				+" where ID_SINTOMAS= :ID_SINTOMAS");
						query.setInteger("ID_SINTOMAS", sintomas.getID_SINTOMAS());
						query.setParameter("NOME_SINTOMAS", sintomas.getNOME_SINTOMAS());
						query.setParameter("DESCRICAO", sintomas.getDESCRICAO());
						query.setParameter("ID_REMEDIO", sintomas.getID_REMEDIO());
						
						query.executeUpdate(); 
				t.commit();
	}

	public List<Sintomas> pesquisar(String NOME_SINTOMAS) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session
				.createSQLQuery("Select NOME_SINTOMAS,DESCRICAO,ID_SINTOMAS , R.NOME  as NOME_REMEDIO FROM Sintomas as s inner join remedio as r on r.ID_REMEDIO = s.ID_REMEDIO  where s.NOME_SINTOMAS LIKE :NOME_SINTOMAS"); 
																										
		query.setParameter("NOME_SINTOMAS","%"+ NOME_SINTOMAS + "%" );
		query.addScalar("NOME_SINTOMAS");
		query.addScalar("DESCRICAO");
		query.addScalar("ID_SINTOMAS");
		query.addScalar("NOME_REMEDIO");

		query.setResultTransformer( Transformers.aliasToBean( Sintomas.class ) );

	    List<Sintomas> users = query.list();

		
		return users;
	}

	
	public List<Sintomas> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session
				.createSQLQuery("Select NOME_SINTOMAS,DESCRICAO,ID_SINTOMAS , R.NOME  as NOME_REMEDIO FROM Sintomas as s inner join remedio as r on r.ID_REMEDIO = s.ID_REMEDIO "); 
																										
		query.addScalar("NOME_SINTOMAS");
		query.addScalar("DESCRICAO");
		query.addScalar("ID_SINTOMAS");
		query.addScalar("NOME_REMEDIO");

		query.setResultTransformer( Transformers.aliasToBean( Sintomas.class ) );

	    List<Sintomas> users = query.list();

		
		return users;
	}
}
