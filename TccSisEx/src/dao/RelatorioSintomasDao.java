package dao;

import java.util.List;

import model.RelatorioSintomas;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class RelatorioSintomasDao {
	
	public List<RelatorioSintomas> relatorioSintomas(String nomeSintomas, String nomeSetor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
		// mudar query
		SQLQuery query = session.createSQLQuery("select sintomas as nomeSintomas, s.nome as nomeSetor, count(*) as qtdSintomas from registro_entrada as r inner join funcionario as f on f.setor = f.setor inner join setor as s on s.nome = s.nome  where r.sintomas = :nomeSintomas and s.nome = :nomeSetor group by  sintomas, s.nome");
		query.setParameter("nomeSintomas", nomeSintomas);
		query.setParameter("nomeSetor", nomeSetor);
	    query.addScalar("nomeSintomas");
	    query.addScalar("nomeSetor");
	    query.addScalar("qtdSintomas");

	    query.setResultTransformer( Transformers.aliasToBean( RelatorioSintomas.class ) );

	    List<RelatorioSintomas> users = query.list();
	    return users;
		
	
	
	
	}
		//mudar query
	public List<RelatorioSintomas> relatorioSintomasTodos(String nomeSintomas , String nomeSetor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
	
		SQLQuery query = session.createSQLQuery("select sintomas as nomeSintomas, s.nome as nomeSetor, count(*) as qtdSintomas from registro_entrada as r inner join funcionario as f on f.setor = f.setor inner join setor as s on s.nome = s.nome  group by  sintomas, s.nome");
		
	    query.addScalar("nomeSintomas");
	    query.addScalar("nomeSetor");
	    query.addScalar("qtdSintomas");
	    query.setResultTransformer( Transformers.aliasToBean( RelatorioSintomas.class ) );

	    List<RelatorioSintomas> users = query.list();
	    return users;
	
	
	
	
	}
}
	
	

