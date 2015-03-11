package dao;

import java.util.List;

import model.Exame;

public interface ExameDao {

	public void save(Exame exame);


	public List<Exame> list();

	public void remove(Exame exame);

	public void update(Exame exame);
	
	public List<Exame> pesquisar(Integer id_exame);
	
	public List<Exame> pesquisarNome(String nome_exame);
	

}