package dao;

import java.util.List;


import model.RelatorioGerencial;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class RelatorioGerencialDao {
	
	
	
	public List<RelatorioGerencial> relatorioGerencial(String nomeMedico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
	
		SQLQuery query = session.createSQLQuery("select medico as nomeMedico, tipo_entrada as tipoEntrada , count(*) as qtdeEntrada from registro_entrada where medico = :nomeMedico group by tipo_entrada , medico");
		query.setParameter("nomeMedico", nomeMedico);
	    query.addScalar("nomeMedico");
	    query.addScalar("tipoEntrada");
	    query.addScalar("qtdeEntrada");

	    query.setResultTransformer( Transformers.aliasToBean( RelatorioGerencial.class ) );

	    List<RelatorioGerencial> users = query.list();
	    return users;
		
	
	
	
	}
	
	public List<RelatorioGerencial> relatorioGerencialTodos(String nomeMedico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
	
		SQLQuery query = session.createSQLQuery("select medico as nomeMedico, tipo_entrada as tipoEntrada , count(*) as qtdeEntrada from registro_entrada group by tipo_entrada , medico");
		
	    query.addScalar("nomeMedico");
	    query.addScalar("tipoEntrada");
	    query.addScalar("qtdeEntrada");

	    query.setResultTransformer( Transformers.aliasToBean( RelatorioGerencial.class ) );

	    List<RelatorioGerencial> users = query.list();
	    return users;
	
	
	
	
	}
}




