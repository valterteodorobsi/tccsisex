package dao;

import java.util.List;

import model.Funcionario;
import model.FuncionarioPes;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class FuncionarioDaoImp implements FuncionarioDao {

	public void save(Funcionario funcionario) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(funcionario);
		t.commit();
	}


	public List<Funcionario> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Funcionario").list();
		t.commit();
		return lista;
	}

	public void remove(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(funcionario);
		t.commit();
	}

	public void update(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		System.out.println(funcionario.getNOME());
		SQLQuery query = session.createSQLQuery("update funcionario set  NOME = funcionario.getNOME() , ramal = :funcionario.getRAMAL() , endereco = :funcionario.getENDERECO() , sexo = :funcionario.getSEXO() , rg = :funcionario.getRG() , email =:funcionario.getEMAIL(), data_nasc = :funcionario.getDATA_NAS() , id_funcao = :funcionario.getID_FUNCAO() , id_centro_custo = :funcionario.getID_CENTRO_CUSTO() where id_matricula = :funcionario.getID_MATRICULA()");
		query.setParameter("NOME",funcionario.getNOME());
		query.setParameter(	"ramal" , funcionario.getRAMAL());
		query.setParameter("endereco" ,funcionario.getENDERECO());
		query.setParameter("sexo" , funcionario.getSEXO());
		query.setParameter("rg", funcionario.getRAMAL());
		query.setParameter("email" , funcionario.getEMAIL());
		query.setParameter("data_nasc" ,funcionario.getDATA_NASC());
		query.setParameter("id_funcao" ,funcionario.getID_FUNCAO());
		query.setParameter("id_centro_custo",funcionario.getID_CENTRO_CUSTO());
		
		session.update(funcionario);
		
		t.commit();
	}
	public List<FuncionarioPes> pesquisar(Integer matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select DATA_NASC, EMAIL , ENDERECO, ID_MATRICULA, funcionario.NOME as NOME, RAMAL, RG, SEXO, f.NOME as NOMEFUN, s.NOME as NOMESET FROM funcionario inner join setor as s on s.ID_CENTRO_CUSTO = funcionario.ID_CENTRO_CUSTO inner join funcao as f on f.ID_FUNCAO = funcionario.ID_FUNCAO where ID_MATRICULA = :matricula group by s.NOME , f.NOME, ID_MATRICULA, funcionario.NOME, DATA_NASC, EMAIL, ENDERECO , RAMAL , RG, SEXO"); 
		query.setParameter("matricula", matricula);
		query.addScalar("DATA_NASC");
		query.addScalar("EMAIL");
		query.addScalar("ENDERECO");
		query.addScalar("ID_MATRICULA");
		query.addScalar("NOME");
		query.addScalar("RAMAL");
		query.addScalar("RG");
		query.addScalar("SEXO");
		query.addScalar("NOMEFUN");
		query.addScalar("NOMESET");
		query.setResultTransformer( Transformers.aliasToBean( Funcionario.class ) );

	    List<FuncionarioPes> users = query.list();

		
		return users;
		
	}
	
	public List<Funcionario> pesquisarNome(String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("FROM Funcionario u where u.NOME LIKE :nome"); 
		query.setParameter("nome", "%"+ nome + "%");
		
		t.commit();
		return query.list();
		
	}
}