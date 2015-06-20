package dao;

import java.util.Date;
import java.util.List;

import model.RelatorioPermanencia;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class RelatorioPermanenciaDao {

	
	public List<RelatorioPermanencia> RelatorioPermanencia(String nomeFuncionario, Date dataInicial, Date dataFinal) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
	// alterar o select e o nome das variaveis
		SQLQuery query = session
				.createSQLQuery("SELECT REGISTRO.ID_MATRICULA, FUNCIONARIO.NOME AS nomeFuncionario"
						+ ",COUNT(REGISTRO.ID_MATRICULA) AS QtdEntrada"
						+ ",SUM(DATEDIFF(minute, DATA_ENTRADA, DATA_SAIDA)) AS SOMA_DATA_DIFF"
						+ ",(SUM(DATEDIFF(minute, DATA_ENTRADA, DATA_SAIDA))/COUNT(REGISTRO.ID_MATRICULA)) AS tempoMedidoPermanenciaMinutos"
						+ ", (SUM(DATEDIFF (HOUR, DATA_ENTRADA, DATA_SAIDA))/COUNT(REGISTRO.ID_MATRICULA)) AS tempoMedioPermanenciaHoras"
						+ " FROM Registro_Entrada AS REGISTRO INNER JOIN FUNCIONARIO AS Funcionario ON REGISTRO.ID_MATRICULA = FUNCIONARIO.ID_MATRICULA"
						+ " WHERE FUNCIONARIO.NOME = :nomeFuncionario and REGISTRO.DATA_ENTRADA >=:dataInicial and REGISTRO.DATA_SAIDA <=:dataFinal "
						+ "GROUP BY REGISTRO.ID_MATRICULA , FUNCIONARIO.NOME");
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		query.setParameter("nomeFuncionario", nomeFuncionario);
		query.addScalar("nomeFuncionario");
		query.addScalar("tempoMedidoPermanenciaMinutos");
	    query.addScalar("tempoMedioPermanenciaHoras");
	    query.addScalar("QtdEntrada");

	    query.setResultTransformer( Transformers.aliasToBean( RelatorioPermanencia.class ) );

	    List<RelatorioPermanencia> users = query.list();
	    return users;
		
	
	
	
	}
	
	public List<RelatorioPermanencia> RelatorioPermanenciaTodos(String nomeFuncionario, Date dataInicial, Date dataFinal) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
	
		SQLQuery query = session
				.createSQLQuery("SELECT REGISTRO.ID_MATRICULA, FUNCIONARIO.NOME AS nomeFuncionario"
						+ ",COUNT(REGISTRO.ID_MATRICULA) AS QtdEntrada"
						+ ",SUM(DATEDIFF(minute, DATA_ENTRADA, DATA_SAIDA)) AS SOMA_DATA_DIFF"
						+ ",(SUM(DATEDIFF(minute, DATA_ENTRADA, DATA_SAIDA))/COUNT(REGISTRO.ID_MATRICULA)) AS tempoMedidoPermanenciaMinutos"
						+ ", (SUM(DATEDIFF (HOUR, DATA_ENTRADA, DATA_SAIDA))/COUNT(REGISTRO.ID_MATRICULA)) AS tempoMedioPermanenciaHoras"
						+ " FROM Registro_Entrada AS REGISTRO INNER JOIN FUNCIONARIO AS Funcionario ON REGISTRO.ID_MATRICULA = FUNCIONARIO.ID_MATRICULA"
						+ "  where REGISTRO.DATA_ENTRADA >= :dataInicial and REGISTRO.DATA_SAIDA <=:dataFinal"
						+ " GROUP BY REGISTRO.ID_MATRICULA , FUNCIONARIO.NOME");
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		query.addScalar("nomeFuncionario");
		query.addScalar("tempoMedidoPermanenciaMinutos");
	    query.addScalar("tempoMedioPermanenciaHoras");
	    query.addScalar("QtdEntrada");

	    query.setResultTransformer( Transformers.aliasToBean( RelatorioPermanencia.class ) );

	    List<RelatorioPermanencia> users = query.list();
	    return users;
	}
}
