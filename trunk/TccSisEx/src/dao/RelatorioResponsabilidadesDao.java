package dao;

import java.util.List;

import model.RelatorioResponsabilidades;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class RelatorioResponsabilidadesDao {

	
	public List<RelatorioResponsabilidades> RelatorioResponsabilidades(String nomeSetor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
		SQLQuery query = session
				.createSQLQuery("select COUNT(PENDENCIAS)as pendencias , f.nome as nomeFuncionario, s.nome as nomeSetor from PRONTUARIO as p "
						+ "inner join FUNCIONARIO as f on f.ID_MATRICULA = p.ID_MATRICULA "
						+ "inner join SETOR as s on s.ID_CENTRO_CUSTO = f.ID_CENTRO_CUSTO "
						+ "where s.NOME = :nomeSetor and p.PENDENCIAS = 'sim' "
						+ "group by f.NOME, s.nome ");
		query.setParameter("nomeSetor", nomeSetor);
		query.addScalar("nomeSetor");
		query.addScalar("pendencias");
	    query.addScalar("nomeFuncionario");
	    
	    query.setResultTransformer( Transformers.aliasToBean( RelatorioResponsabilidades.class ) );

	    List<RelatorioResponsabilidades> users = query.list();
	    return users;
		
	
	
	
	}
	
	public List<RelatorioResponsabilidades> RelatorioResponsabilidadesTodos(String nomeSetor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
	
		SQLQuery query = session
				.createSQLQuery("select COUNT(PENDENCIAS)as pendencias , f.nome as nomeFuncionario, s.nome as nomeSetor from PRONTUARIO as p "
						+ "inner join FUNCIONARIO as f on f.ID_MATRICULA = p.ID_MATRICULA "
						+ "inner join SETOR as s on s.ID_CENTRO_CUSTO = f.ID_CENTRO_CUSTO "
						+ "where p.PENDENCIAS = 'sim' "
						+ "group by f.NOME, s.nome ");
		query.addScalar("nomeSetor");
		query.addScalar("pendencias");
	    query.addScalar("nomeFuncionario");

	    query.setResultTransformer( Transformers.aliasToBean( RelatorioResponsabilidades.class ) );

	    List<RelatorioResponsabilidades> users = query.list();
	    return users;
	}
}
