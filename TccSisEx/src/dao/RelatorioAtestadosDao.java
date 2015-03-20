package dao;

import java.util.List;

import model.RelatorioAtestado;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class RelatorioAtestadosDao {
	public List<RelatorioAtestado> RelatorioAtestado(String nomeColaborador, String nomeSetor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
		
		SQLQuery query = session.createSQLQuery("select nome_colaborador as nomeColaborador , setor as nomeSetor ,  count(*) as qtdAtestados from anexo_atestado where nome_colaborador = :nomeColaborador and setor = :nomeSetor group by  nome_colaborador , setor");
		query.setParameter("nomeColaborador", nomeColaborador);
		query.setParameter("nomeSetor", nomeSetor);
	    query.addScalar("nomeColaborador");
	    query.addScalar("nomeSetor");
	    query.addScalar("qtdAtestados");

	    query.setResultTransformer( Transformers.aliasToBean( RelatorioAtestado.class ) );

	    List<RelatorioAtestado> users = query.list();
	    return users;
		
	
	
	
	}
		
	public List<RelatorioAtestado> relatorioAtestadoTodos(String nomeColaborador , String nomeSetor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
	
		SQLQuery query = session.createSQLQuery(" select  nome_colaborador as nomeColaborador , setor as nomeSetor, count(*) as qtdAtestados  from anexo_atestado  group by nome_colaborador, setor");
	    query.addScalar("nomeColaborador");
	    query.addScalar("nomeSetor");
	    query.addScalar("qtdAtestados");
	    query.setResultTransformer( Transformers.aliasToBean( RelatorioAtestado.class ) );

	    List<RelatorioAtestado> users = query.list();
	    return users;
	
	
	
	
	}
}
