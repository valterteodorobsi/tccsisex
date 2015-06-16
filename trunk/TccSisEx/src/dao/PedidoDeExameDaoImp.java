package dao;

import model.PedidoDeExame;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class PedidoDeExameDaoImp implements PedidoDeExameDao {

	public void save(PedidoDeExame pedidoDeExame)throws Exception {
	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try {
			SQLQuery query = session
					.createSQLQuery("insert into Solicitacao_exame (nome_colaborador, data_nasc, cid, nome_medico, matricula, setor, funcao, nome_exame, descricao)"
							+ " values( :nome_colaborador, :data_nasc, :cid, :nome_medico, :matricula, :setor, :funcao, :nome_exame, :descricao)");
			query.setParameter("data_nasc", pedidoDeExame.getDATA_NASC());
			query.setParameter("cid", pedidoDeExame.getCID() );
			query.setParameter("nome_colaborador", pedidoDeExame.getNOME_COLABORADOR());
			query.setParameter("nome_medico", pedidoDeExame.getNOME_MEDICO());
			query.setParameter("matricula", pedidoDeExame.getMATRICULA());
			query.setParameter("setor", pedidoDeExame.getSETOR());
			query.setParameter("funcao", pedidoDeExame.getFUNCAO());
			query.setParameter("nome_exame", pedidoDeExame.getNOME_EXAME());
			query.setParameter("descricao", pedidoDeExame.getDESCRICAO());
			query.executeUpdate();

		} catch (Exception ex) {

			session.close();
			throw ex;

		}
		t.commit();
	}
	

	

}
