package dao;

import java.util.List;

import model.Funcionario;
import model.FuncionarioPes;
import model.RegistroEntrada;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class RegistroEntradaDaoImp implements RegistroEntradaDao {


	public void save(RegistroEntrada registro) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("insert into REGISTRO_ENTRADA (ID_MATRICULA, ID_MEDICO, ID_REMEDIO, ID_SINTOMAS,  DATA_ENTRADA, DESCRICAO,  ENC_EX, TIPO_ENTRADA)" +
					" values(:ID_MATRICULA, :ID_MEDICO, :ID_REMEDIO, :ID_SINTOMAS, :DATA_ENTRADA, :DESCRICAO,  :ENC_EX, :TIPO_ENTRADA )");
					query.setParameter("ID_MATRICULA", registro.getID_MATRICULA());
					query.setParameter("ID_MEDICO", registro.getID_MEDICO());
					query.setParameter("ID_REMEDIO", registro.getID_REMEDIO());
					query.setParameter("ID_SINTOMAS", registro.getID_SINTOMAS());
					query.setParameter("DATA_ENTRADA", registro.getDATA_ENTRADA());
					query.setParameter("DESCRICAO", registro.getDESCRICAO());
					query.setParameter("ENC_EX", registro.getEncExt());
					query.setParameter("TIPO_ENTRADA", registro.isEmergencial());
					
					query.executeUpdate();
					t.commit();
		
	}
	
	
	public List<RegistroEntrada> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Registro_entrada").list();
		t.commit();
		return lista;
	}
	public List<RegistroEntrada> pesquisar(Integer ID_MATRICULA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("Select f.nome as NOME_COLABORADOR, rs.ID_MATRICULA, r.nome as REMEDIO, rs.DESCRICAO, rs.DATA_ENTRADA from REGISTRO_ENTRADA as rs inner join FUNCIONARIO as f on rs.ID_MATRICULA = f.ID_MATRICULA inner join REMEDIO as r on rs.ID_REMEDIO = r.ID_REMEDIO where rs.ID_MATRICULA = :ID_MATRICULA and rs.DATA_SAIDA  IS NULL"); 
		query.setParameter("ID_MATRICULA", ID_MATRICULA);		
		query.addScalar("NOME_COLABORADOR");
		query.addScalar("ID_MATRICULA");
		query.addScalar("REMEDIO");
		query.addScalar("DESCRICAO");
		query.addScalar("DATA_ENTRADA");
		query.setResultTransformer( Transformers.aliasToBean( RegistroEntrada.class ) );

	    List<RegistroEntrada> users = query.list();
		return users;
																			
																										

	}
	
	
	public void update(Integer id_matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		/*session.update(registro);
		t.commit();*/
		Query query = session
				.createSQLQuery("UPDATE REGISTRO_ENTRADA SET DATA_SAIDA = GETDATE() WHERE ID_MATRICULA = :id_matricula");
		
		query.setParameter("id_matricula", id_matricula);																								// depois
		query.executeUpdate();
		t.commit();
	}
}
