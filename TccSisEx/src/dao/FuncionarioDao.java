package dao;

import java.util.List;

import model.Funcionario;
import model.FuncionarioPes;

public interface FuncionarioDao {

	public void save(Funcionario funcionario);

	public List<Funcionario> list();

	public void remove(Funcionario funcionario);

	public void update(Funcionario funcionario);
	
	public List<FuncionarioPes> pesquisar(Integer matricula);
	
	public List<Funcionario> pesquisarNome( String nome);

}