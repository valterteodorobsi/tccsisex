package dao;

import java.util.List;

import model.Funcionario;

import model.FuncionarioPes;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;
import controller.FuncionarioController;

public class FuncionarioDaoImp implements FuncionarioDao {

	public void save(Funcionario funcionario) throws  Exception {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try{
		SQLQuery query = session.createSQLQuery("insert into Funcionario (ID_MATRICULA, NOME, ID_CENTRO_CUSTO, ID_FUNCAO,  DATA_NASC, RG,  EMAIL, SEXO, RAMAL)" +
		" values(:ID_MATRICULA, :NOME, :ID_CENTRO_CUSTO, :ID_FUNCAO, :DATA_NASC, :RG,  :EMAIL, :SEXO, :RAMAL )");
		query.setParameter("ID_MATRICULA", funcionario.getID_MATRICULA());
		query.setParameter("NOME", funcionario.getNOME());
		query.setParameter("ID_CENTRO_CUSTO", funcionario.getID_CENTRO_CUSTO());
		query.setParameter("ID_FUNCAO", funcionario.getID_FUNCAO());
		query.setParameter("DATA_NASC", funcionario.getDATA_NASC());
		query.setParameter("RG", funcionario.getRG());
		query.setParameter("EMAIL", funcionario.getEMAIL());
		query.setParameter("SEXO", funcionario.getSEXO());
		query.setParameter("RAMAL", funcionario.getRAMAL());
		 
		  
	query.executeUpdate();
	
		}catch(Exception ex){
			  
			//session.refresh(funcionario);
		       	session.close();
		       	FuncionarioController.matriculaErro();
		        throw ex;
			 
		}
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
		SQLQuery query = session.createSQLQuery("DELETE FROM Funcionario WHERE ID_MATRICULA = :ID_MATRICULA");
		query.setInteger("ID_MATRICULA", funcionario.getID_MATRICULA());
		query.executeUpdate(); 
		t.commit();
	}

	public void update(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();

		System.out.println(funcionario);
		SQLQuery query = session.createSQLQuery("update Funcionario set NOME=:NOME, ID_CENTRO_CUSTO =:ID_CENTRO_CUSTO, ID_FUNCAO = :ID_FUNCAO, DATA_NASC = :DATA_NASC, RG = :RG, EMAIL = :EMAIL, SEXO = :SEXO, RAMAL= :RAMAL"
		+" where ID_MATRICULA = :ID_MATRICULA");
				query.setInteger("ID_MATRICULA", funcionario.getID_MATRICULA());
				query.setParameter("NOME", funcionario.getNOME());
				query.setParameter("ID_CENTRO_CUSTO", funcionario.getID_CENTRO_CUSTO());
				query.setParameter("ID_FUNCAO", funcionario.getID_FUNCAO());
				query.setParameter("DATA_NASC", funcionario.getDATA_NASC());
				query.setParameter("RG", funcionario.getRG());
				query.setParameter("EMAIL", funcionario.getEMAIL());
				query.setParameter("SEXO", funcionario.getSEXO());
				query.setParameter("RAMAL", funcionario.getRAMAL());
				
				query.executeUpdate(); 

		/*System.out.println(funcionario.getNOME());
		SQLQuery query = session.createSQLQuery("update funcionario set  NOME = funcionario.getNOME() , ramal = :funcionario.getRAMAL() , sexo = :funcionario.getSEXO() , rg = :funcionario.getRG() , email =:funcionario.getEMAIL(), data_nasc = :funcionario.getDATA_NAS() , id_funcao = :funcionario.getID_FUNCAO() , id_centro_custo = :funcionario.getID_CENTRO_CUSTO() where id_matricula = :funcionario.getID_MATRICULA()");
		query.setParameter("NOME",funcionario.getNOME());
		query.setParameter(	"ramal" , funcionario.getRAMAL());
		query.setParameter("sexo" , funcionario.getSEXO());
		query.setParameter("rg", funcionario.getRAMAL());
		query.setParameter("email" , funcionario.getEMAIL());
		query.setParameter("data_nasc" ,funcionario.getDATA_NASC());
		query.setParameter("id_funcao" ,funcionario.getID_FUNCAO());
		query.setParameter("id_centro_custo",funcionario.getID_CENTRO_CUSTO());
		
		session.update(funcionario);*/
		

		t.commit();
	}
	public List<FuncionarioPes> pesquisar(Integer matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select DATA_NASC, EMAIL , ID_MATRICULA, funcionario.NOME as NOME, RAMAL, RG, SEXO, f.NOME as NOMEFUN, s.NOME as NOMESET FROM funcionario inner join setor as s on s.ID_CENTRO_CUSTO = funcionario.ID_CENTRO_CUSTO inner join funcao as f on f.ID_FUNCAO = funcionario.ID_FUNCAO where ID_MATRICULA = :matricula group by s.NOME , f.NOME, ID_MATRICULA, funcionario.NOME, DATA_NASC, EMAIL, RAMAL , RG, SEXO"); 
		query.setParameter("matricula", matricula);
		query.addScalar("DATA_NASC");
		query.addScalar("EMAIL");
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
		//Query query = session.createQuery("FROM Funcionario u where u.NOME LIKE :nome"); 
		
		
		SQLQuery query = session.createSQLQuery("select DATA_NASC, EMAIL , ID_MATRICULA, fu.NOME as NOME, RAMAL, RG, SEXO, f.NOME as NOMEFUN, s.NOME as NOMESET FROM funcionario as fu inner join setor as s on s.ID_CENTRO_CUSTO = fu.ID_CENTRO_CUSTO inner join funcao as f on f.ID_FUNCAO = fu.ID_FUNCAO where fu.NOME LIKE :nome group by s.NOME , f.NOME, ID_MATRICULA, fu.NOME, DATA_NASC, EMAIL, RAMAL , RG, SEXO"); 
		query.setParameter("nome", "%"+ nome + "%");
		query.addScalar("DATA_NASC");
		query.addScalar("EMAIL");
		query.addScalar("ID_MATRICULA");
		query.addScalar("NOME");
		query.addScalar("RAMAL");
		query.addScalar("RG");
		query.addScalar("SEXO");
		query.addScalar("NOMEFUN");
		query.addScalar("NOMESET");
		query.setResultTransformer( Transformers.aliasToBean( Funcionario.class ) );

	    List<Funcionario> users = query.list();

		
		return users;
		
	}
	
	public List<Funcionario> listaNome(Integer matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select nome FROM Funcionario where ID_MATRICULA =:matricula"); 
		query.setParameter("matricula", matricula );
		query.addScalar("NOME");
		List<Funcionario> users = query.list();

		return users;
		}

}