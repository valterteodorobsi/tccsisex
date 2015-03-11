package dao;

import model.Funcionario;
import java.util.List;

public interface FuncionarioDao {

	public void save(Funcionario funcionario);

	public List<Funcionario> list();

	public void remove(Funcionario funcionario);

	public void update(Funcionario funcionario);
	
	public List<Funcionario> pesquisar(Integer matricula);
	
	public List<Funcionario> pesquisarNome( String nome);

}