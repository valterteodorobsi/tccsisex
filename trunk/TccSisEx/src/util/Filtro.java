package util;

import org.hibernate.Session;

public class Filtro {
	Session session = null;

	public Filtro() {
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
}
