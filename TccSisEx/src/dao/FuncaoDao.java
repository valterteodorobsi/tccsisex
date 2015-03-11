package dao;

import model.Funcao;
import java.util.List;

public interface FuncaoDao {

	public void save(Funcao funcao);

	public List<Funcao> list();

	public void remove(Funcao funcao);

	public void update(Funcao funcao);
	
	public List<Funcao> pesquisar(int matricula);
	
	public List<Funcao> pesquisarNome(String nome);

}