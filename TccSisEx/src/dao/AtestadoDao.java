package dao;

import java.util.List;

import model.Atestado;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class AtestadoDao {

	
	public void save(Atestado atestado) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("insert into ANEXO_ATESTADO (ID_MATRICULA, ID_SETOR, CID, IMAGEM, DIAS, DATA_INCLUSAO )" +
				" values(:ID_MATRICULA, :ID_SETOR, :CID, :IMAGEM, :DIAS, :DATA_INCLUSAO )");
				query.setParameter("ID_MATRICULA", atestado.getMatricula());
				query.setParameter("ID_SETOR", atestado.getID_SETOR());
				query.setParameter("CID", atestado.getCid());
				query.setParameter("IMAGEM", atestado.getImagem());
				query.setParameter("DIAS", atestado.getDIAS());
				query.setParameter("DATA_INCLUSAO", atestado.getDATA_INCLUSAO()); 
			query.executeUpdate();
		t.commit();
	}
	
	
	public List<Atestado> pesquisar(int matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select an.ID_ATESTADO as idAtestado ,an.ID_MATRICULA, "
				+ " an.DIAS,an.DATA_INCLUSAO, f.NOME as nomeColaborador, s.NOME as setor, CID ,an.imagem "
				+ " from ANEXO_ATESTADO as an  inner join FUNCIONARIO as f on an.ID_MATRICULA = f.ID_MATRICULA "
				+ " inner join SETOR as s on an.ID_SETOR = s.ID_CENTRO_CUSTO where an.ID_MATRICULA = :matricula"); 
		query.setParameter("matricula", matricula);
		query.addScalar("ID_MATRICULA");
		query.addScalar("CID");
		query.addScalar("DIAS");
		query.addScalar("imagem");
		query.addScalar("nomeColaborador");
		query.addScalar("setor");
		query.addScalar("idAtestado");
		query.addScalar("DATA_INCLUSAO");
		query.setResultTransformer( Transformers.aliasToBean( Atestado.class ) );
		
	    List<Atestado> users = query.list();
		return users;
		

	}
	
	public List<Atestado> pesquisarTodos() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select an.ID_ATESTADO as idAtestado ,an.ID_MATRICULA, an.DIAS, "
				+ " an.DATA_INCLUSAO, f.NOME as nomeColaborador, s.NOME as setor, CID ,an.imagem "
				+ " from ANEXO_ATESTADO as an  inner join FUNCIONARIO as f on an.ID_MATRICULA = f.ID_MATRICULA "
				+ " inner join SETOR as s on an.ID_SETOR = s.ID_CENTRO_CUSTO "); 
		query.addScalar("ID_MATRICULA");
		query.addScalar("CID");
		query.addScalar("DIAS");
		query.addScalar("imagem");
		query.addScalar("nomeColaborador");
		query.addScalar("setor");
		query.addScalar("idAtestado");
		query.addScalar("DATA_INCLUSAO");
		query.setResultTransformer( Transformers.aliasToBean( Atestado.class ) );
		
	    List<Atestado> users = query.list();
		return users;
		

	}
	
	public void remove(Atestado atestado) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("DELETE FROM ANEXO_ATESTADO WHERE ID_ATESTADO = :idAtestado");
		query.setInteger("idAtestado", atestado.getIdAtestado());
		query.executeUpdate(); 
		t.commit();	}
}
