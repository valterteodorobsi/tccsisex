package dao;

import java.util.List;

import model.Setor;

public interface SetorDao {

	public void save(Setor setor);

	public List<Setor> list();

	public void remove(Setor setor);

	public void update(Setor setor);
	
	public List<Setor> pesquisar(Integer ID_CENTRO_CUSTO);
	
	public List<Setor> pesquisarNome(String nome);
	

}