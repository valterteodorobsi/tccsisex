package dao;

import java.util.Date;
import java.util.List;

import model.RelatorioOcorrencia;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class RelatorioOcorrenciaDao {
	public List<RelatorioOcorrencia> relatorioOcorrencia(Integer nomeColaborador, Integer nomeSetor, Date dataInicial, Date dataFinal) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
		
		SQLQuery query = session
				.createSQLQuery("select f.nome as nomeColaborador , enc_ex as encaminhamento ,  seto.nome as nomeSetor,"
						+ " COUNT(enc_ex) as qtdEncaminhamento from registro_entrada as r"
						+ " inner join funcionario AS f on f.ID_MATRICULA = r.ID_MATRICULA"
						+ " inner join setor AS seto on f.ID_CENTRO_CUSTO = seto.ID_CENTRO_CUSTO"
						+ " where seto.ID_CENTRO_CUSTO = :nomeSetor and f.ID_MATRICULA = :nomeColaborador  and r.DATA_ENTRADA >= :dataInicial and r.DATA_SAIDA <= :dataFinal "
						+ " group by enc_ex, f.nome ,  seto.nome");
		query.setParameter("nomeColaborador", nomeColaborador);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		query.setParameter("nomeSetor", nomeSetor);
		query.addScalar("nomeColaborador");
		query.addScalar("nomeSetor");
		query.addScalar("qtdEncaminhamento");
		query.addScalar("encaminhamento");
	    
	    query.setResultTransformer( Transformers.aliasToBean( RelatorioOcorrencia.class ) );

	    List<RelatorioOcorrencia> users = query.list();
	    return users;
		
	
	
	
	}
		
	public List<RelatorioOcorrencia> relatorioOcorrenciaTodos(Integer nomeColaborador , Integer nomeSetor, Date dataInicial, Date dataFinal) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
	
		SQLQuery query = session
				.createSQLQuery(" select f.nome as nomeColaborador , enc_ex as encaminhamento ,  seto.nome as nomeSetor,"
						+ " COUNT(enc_ex) as qtdEncaminhamento from registro_entrada as r"
						+ " inner join funcionario AS f on f.ID_MATRICULA = r.ID_MATRICULA"
						+ " inner join setor AS seto on f.ID_CENTRO_CUSTO = seto.ID_CENTRO_CUSTO"
						+ " where r.DATA_ENTRADA >= :dataInicial and r.DATA_SAIDA <= :dataFinal "
						+ " group by enc_ex, f.nome ,  seto.nome");
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		query.addScalar("nomeColaborador");
		query.addScalar("nomeSetor");
		query.addScalar("qtdEncaminhamento");
		query.addScalar("encaminhamento");
	    
	    query.setResultTransformer( Transformers.aliasToBean( RelatorioOcorrencia.class ) );

	    List<RelatorioOcorrencia> users = query.list();
	    return users;
	
	
	
	
	}
}
