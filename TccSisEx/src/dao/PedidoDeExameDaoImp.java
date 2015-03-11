package dao;

import model.PedidoDeExame;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class PedidoDeExameDaoImp implements PedidoDeExameDao {

	public void save(PedidoDeExame pedidoDeExame) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(pedidoDeExame);
		t.commit();
	}

	

	

}
