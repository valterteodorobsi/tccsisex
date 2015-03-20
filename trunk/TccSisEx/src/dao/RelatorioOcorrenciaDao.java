package dao;

import java.util.List;

import model.RelatorioOcorrencia;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class RelatorioOcorrenciaDao {
	public List<RelatorioOcorrencia> relatorioOcorrencia(String nomeColaborador, String nomeSetor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
		
		SQLQuery query = session.createSQLQuery("select nome_colaborador as nomeColaborador , enc_ex as encaminhamento ,  f.setor as nomeSetor , COUNT(enc_ex) as qtdEncaminhamento  from registro_entrada as r inner join funcionario AS f on f.nome = r.nome_colaborador  where f.SETOR = :nomeSetor and f.nome = :nomeColaborador group by nome_colaborador  , enc_ex ,  f.setor");
		query.setParameter("nomeColaborador", nomeColaborador);
		query.setParameter("nomeSetor", nomeSetor);
	    query.addScalar("nomeColaborador");
	    query.addScalar("nomeSetor");
	    query.addScalar("qtdEncaminhamento");
	    query.addScalar("encaminhamento");
	    
	    query.setResultTransformer( Transformers.aliasToBean( RelatorioOcorrencia.class ) );

	    List<RelatorioOcorrencia> users = query.list();
	    return users;
		
	
	
	
	}
		
	public List<RelatorioOcorrencia> relatorioOcorrenciaTodos(String nomeColaborador , String nomeSetor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
	
		SQLQuery query = session.createSQLQuery(" select nome_colaborador as nomeColaborador , enc_ex as encaminhamento ,  f.setor as nomeSetor , COUNT(enc_ex) as qtdEncaminhamento  from registro_entrada as r inner join funcionario AS f on f.nome = r.nome_colaborador  group by nome_colaborador  , enc_ex ,  f.setor");
	    query.addScalar("nomeColaborador");
	    query.addScalar("nomeSetor");
	    query.addScalar("qtdEncaminhamento");
	    query.addScalar("encaminhamento");
	    
	    query.setResultTransformer( Transformers.aliasToBean( RelatorioOcorrencia.class ) );

	    List<RelatorioOcorrencia> users = query.list();
	    return users;
	
	
	
	
	}
}
