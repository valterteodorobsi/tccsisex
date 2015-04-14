package dao;

import java.util.List;

import model.Arquivo;
import model.Atestado;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class ReceberArquivoDao {
	public void save(Arquivo arquivo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("insert into ANEXO_EXAME (ID_MATRICULA, ID_SETOR, ID_EXAMEFK, CRITICIDADE, DESCRICAO, VALIDADE, IMAGEM )" +
				" values(:ID_MATRICULA, :ID_SETOR,:ID_EXAMEFK, :CRITICIDADE, :DESCRICAO,:VALIDADE, :IMAGEM )");
				query.setParameter("ID_MATRICULA", arquivo.getID_MATRICULA());
				query.setParameter("ID_SETOR", arquivo.getID_SETOR());
				query.setParameter("ID_EXAMEFK", arquivo.getID_EXAMEFK());
				query.setParameter("CRITICIDADE", arquivo.getCRITICIDADE());
				query.setParameter("DESCRICAO", arquivo.getDESCRICAO());
				query.setParameter("VALIDADE", arquivo.getVALIDADE());
				query.setParameter("IMAGEM", arquivo.getIMAGEM());
				 
			query.executeUpdate();
		t.commit();
	}
	
	
	public List<Arquivo> pesquisar(int matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select an.CRITICIDADE, an.DESCRICAO , an.VALIDADE ,an.ID_EXAME, an.ID_MATRICULA ,s.nome as SETOR, f.nome as NOME_COLABORADOR , ex.NOME_EXAME as EXAME from ANEXO_EXAME as an inner join FUNCIONARIO as f on an.ID_MATRICULA = f.ID_MATRICULA inner join SETOR as s on an.ID_SETOR = s.ID_CENTRO_CUSTO inner join EXAME as ex on an.ID_EXAMEFK = ex.ID_EXAME where an.ID_MATRICULA = :matricula"); 
		query.setParameter("matricula", matricula);
		query.addScalar("ID_MATRICULA");
		query.addScalar("CRITICIDADE");
		query.addScalar("DESCRICAO");
		query.addScalar("VALIDADE");
		query.addScalar("ID_EXAME");
		query.addScalar("NOME_COLABORADOR");
		query.addScalar("SETOR");
		query.addScalar("EXAME");
		query.setResultTransformer( Transformers.aliasToBean( Arquivo.class ) );

	    List<Arquivo> users = query.list();
		return users;

	}
	
	
	public void remove(Arquivo arquivo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(arquivo);
		t.commit();
	}
}
