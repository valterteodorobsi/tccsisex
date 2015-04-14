package dao;


import java.util.List;



import org.hibernate.HibernateException;


import model.Funcionario;
import model.FuncionarioPes;


public interface FuncionarioDao {

	public void save(Funcionario funcionario) throws HibernateException, Exception;

	public List<Funcionario> list();

	public void remove(Funcionario funcionario);

	public void update(Funcionario funcionario);
	
	public List<FuncionarioPes> pesquisar(Integer matricula);
	
	public List<FuncionarioPes> pesquisarNome( String nome);

}