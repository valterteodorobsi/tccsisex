package dao;

import java.util.List;


import model.RelatorioGerencial;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class RelatorioGerencialDao {
	
	
	
	public List<RelatorioGerencial> relatorioGerencial(int nomeMedico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		

		SQLQuery query = session.createSQLQuery("select me.NOME as nomeMedicos, tipo_entrada as tipoEntrada , count(*) as qtdeEntrada from registro_entrada as registro inner join medico as me on registro.ID_MEDICO = me.ID_MATRICULA where registro.ID_MEDICO = :nomeMedico group by tipo_entrada , me.NOME");
		query.setParameter("nomeMedico", nomeMedico);
	    query.addScalar("nomeMedicos");
	    query.addScalar("tipoEntrada");
	    query.addScalar("qtdeEntrada");

	    query.setResultTransformer( Transformers.aliasToBean( RelatorioGerencial.class ) );

	    List<RelatorioGerencial> users = query.list();
	    return users;
		
	
	
	
	}
	
	public List<RelatorioGerencial> relatorioGerencialTodos(int nomeMedico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
	
		SQLQuery query = session.createSQLQuery("select me.NOME as nomeMedicos, tipo_entrada as tipoEntrada , count(*) as qtdeEntrada from registro_entrada as registro inner join medico as me on registro.ID_MEDICO = me.ID_MATRICULA group by tipo_entrada , me.NOME");
		
	    query.addScalar("nomeMedicos");
	    query.addScalar("tipoEntrada");
	    query.addScalar("qtdeEntrada");

	    query.setResultTransformer( Transformers.aliasToBean( RelatorioGerencial.class ) );

	    List<RelatorioGerencial> users = query.list();
	    return users;
	
	
	
	
	}
}




