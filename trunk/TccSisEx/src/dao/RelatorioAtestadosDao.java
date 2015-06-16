package dao;

import java.util.List;

import model.RelatorioAtestado;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class RelatorioAtestadosDao {
	public List<RelatorioAtestado> RelatorioAtestado(Integer nomeColaborador, Integer nomeSetor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
		
		SQLQuery query = session.createSQLQuery("select f.NOME as nomeColaborador , s.NOME as nomeSetor ,  count(*) as qtdAtestados from anexo_atestado as anexo inner join FUNCIONARIO as f on anexo.ID_MATRICULA = f.ID_MATRICULA inner join SETOR as s on f.ID_CENTRO_CUSTO = s.ID_CENTRO_CUSTO where f.ID_MATRICULA = :nomeColaborador  or s.ID_CENTRO_CUSTO = :nomeSetor group by  f.nome , s.NOME");
		query.setParameter("nomeColaborador", nomeColaborador);
		query.setParameter("nomeSetor", nomeSetor);
	    query.addScalar("nomeColaborador");
	    query.addScalar("nomeSetor");
	    query.addScalar("qtdAtestados");

	    query.setResultTransformer( Transformers.aliasToBean( RelatorioAtestado.class ) );

	    List<RelatorioAtestado> users = query.list();
	    return users;
		
	
	
	
	}
		
	public List<RelatorioAtestado> relatorioAtestadoTodos(Integer nomeColaborador , Integer nomeSetor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
	
		SQLQuery query = session.createSQLQuery(" select f.NOME as nomeColaborador , s.NOME as nomeSetor ,  count(*) as qtdAtestados from anexo_atestado as anexo inner join FUNCIONARIO as f on anexo.ID_MATRICULA = f.ID_MATRICULA inner join SETOR as s on f.ID_CENTRO_CUSTO = s.ID_CENTRO_CUSTO  group by  f.nome , s.NOME");
		query.addScalar("nomeColaborador");
	    query.addScalar("nomeSetor");
	    query.addScalar("qtdAtestados");

	    query.setResultTransformer( Transformers.aliasToBean( RelatorioAtestado.class ) );

	    List<RelatorioAtestado> users = query.list();
	    return users;
	
	
	
	
	}
}
