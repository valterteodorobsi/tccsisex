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
		
		
		SQLQuery query = session.createSQLQuery("select nome_colaborador as nomeColaborador , enc_ex as encaminhamento ,  seto.nome as nomeSetor, COUNT(enc_ex) as qtdEncaminhamento  from registro_entrada as r inner join funcionario AS f on f.nome = r.nome_colaborador inner join setor AS seto on f.ID_CENTRO_CUSTO = seto.ID_CENTRO_CUSTO where seto.nome = :nomeSetor or f.nome = :nomeColaborador group by nome_colaborador  , enc_ex ,  seto.nome");
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
		
	
		SQLQuery query = session.createSQLQuery(" select nome_colaborador as nomeColaborador , enc_ex as encaminhamento, seto.nome as nomeSetor, COUNT(enc_ex) as qtdEncaminhamento from registro_entrada as r inner join funcionario AS f on f.nome = r.nome_colaborador  inner join setor AS seto on f.ID_CENTRO_CUSTO = seto.ID_CENTRO_CUSTO  group by nome_colaborador  , enc_ex , seto.nome");
	    query.addScalar("nomeColaborador");
	    query.addScalar("nomeSetor");
	    query.addScalar("qtdEncaminhamento");
	    query.addScalar("encaminhamento");
	    
	    query.setResultTransformer( Transformers.aliasToBean( RelatorioOcorrencia.class ) );

	    List<RelatorioOcorrencia> users = query.list();
	    return users;
	
	
	
	
	}
}
