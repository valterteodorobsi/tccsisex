package dao;

import java.util.Date;
import java.util.List;

import model.RelatorioSintomas;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class RelatorioSintomasDao {

	public List<RelatorioSintomas> relatorioSintomas(Integer idSintomas,
			Integer idSetor, Date dataInicial, Date dataFinal) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();

		// mudar query
		SQLQuery query = session
				.createSQLQuery("select sinto.NOME_SINTOMAS as nomeSintomas, s.NOME as nomeSetor, "
						+ " COUNT(*) as qtdSintomas from REGISTRO_ENTRADA as r "
						+ " inner join FUNCIONARIO as funci on funci.ID_MATRICULA = r.ID_MATRICULA "
						+ " inner join SETOR as s on s.ID_CENTRO_CUSTO = funci.ID_CENTRO_CUSTO "
						+ " inner join SINTOMAS as sinto on r.ID_SINTOMAS = sinto.ID_SINTOMAS "
						+ " where s.ID_CENTRO_CUSTO = :idSetor and sinto.ID_SINTOMAS = :idSintomas "
						+ " and r.DATA_ENTRADA >=:dataInicial and r.DATA_SAIDA <= >dataFinal"
						+ " group by sinto.NOME_SINTOMAS , s.NOME");
		query.setParameter("idSintomas", idSintomas);
		query.setParameter("idSetor", idSetor);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		query.addScalar("nomeSintomas");
		query.addScalar("nomeSetor");
		query.addScalar("qtdSintomas");

		query.setResultTransformer(Transformers
				.aliasToBean(RelatorioSintomas.class));

		List<RelatorioSintomas> users = query.list();
		return users;

	}

	// mudar query
	public List<RelatorioSintomas> relatorioSintomasTodos(Integer nomeSintomas,
			Integer nomeSetor, Date dataInicial, Date dataFinal) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();

		SQLQuery query = session
				.createSQLQuery("select sinto.NOME_SINTOMAS as nomeSintomas,"
						+ " s.NOME as nomeSetor, COUNT(*) as qtdSintomas "
						+ " from REGISTRO_ENTRADA as r inner join FUNCIONARIO as funci on "
						+ " funci.ID_MATRICULA = r.ID_MATRICULA inner join SETOR as s on "
						+ " s.ID_CENTRO_CUSTO = funci.ID_CENTRO_CUSTO "
						+ " inner join SINTOMAS as sinto on r.ID_SINTOMAS = sinto.ID_SINTOMAS "
						+ " where r.DATA_ENTRADA >=:dataInicial and r.DATA_SAIDA <=:dataFinal"
						+ " group by sinto.NOME_SINTOMAS , s.NOME");
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		query.addScalar("nomeSintomas");
		query.addScalar("nomeSetor");
		query.addScalar("qtdSintomas");
		query.setResultTransformer(Transformers
				.aliasToBean(RelatorioSintomas.class));

		List<RelatorioSintomas> users = query.list();
		return users;

	}
}
