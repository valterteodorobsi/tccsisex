package dao;

import java.util.Date;
import java.util.List;

import model.RelatorioGerencial;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class RelatorioGerencialDao {

	public List<RelatorioGerencial> relatorioGerencial(int nomeMedico,
			Date dataInicial, Date dataFinal) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();

		SQLQuery query = session
				.createSQLQuery("select me.NOME as nomeMedicos, tipo_entrada as tipoEntrada ,"
						+ " count(*) as qtdeEntrada from registro_entrada as registro"
						+ " inner join medico as me on registro.ID_MEDICO = me.ID_MATRICULA"
						+ " where registro.DATA_ENTRADA >= :dataInicial and registro.DATA_SAIDA <= :dataFinal and registro.ID_MEDICO = :nomeMedico"
						+ " group by tipo_entrada , me.NOME");
		query.setParameter("nomeMedico", nomeMedico);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		query.addScalar("nomeMedicos");
		query.addScalar("tipoEntrada");
		query.addScalar("qtdeEntrada");

		query.setResultTransformer(Transformers
				.aliasToBean(RelatorioGerencial.class));

		List<RelatorioGerencial> users = query.list();
		return users;

	}

	public List<RelatorioGerencial> relatorioGerencialTodos(int nomeMedico,
			Date dataInicial, Date dataFinal) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();

		SQLQuery query = session
				.createSQLQuery("select me.NOME as nomeMedicos, tipo_entrada as tipoEntrada ,"
						+ " count(*) as qtdeEntrada from registro_entrada as registro"
						+ " inner join medico as me on registro.ID_MEDICO = me.ID_MATRICULA"
						+ " where registro.DATA_ENTRADA >= :dataInicial and registro.DATA_SAIDA <= :dataFinal "
						+ " group by tipo_entrada , me.NOME");
		//query.setParameter("nomeMedico", nomeMedico);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		query.addScalar("nomeMedicos");
		query.addScalar("tipoEntrada");
		query.addScalar("qtdeEntrada");

		query.setResultTransformer(Transformers
				.aliasToBean(RelatorioGerencial.class));

		List<RelatorioGerencial> users = query.list();
		return users;

	}
}
