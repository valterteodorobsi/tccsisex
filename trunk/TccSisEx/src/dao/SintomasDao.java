package dao;

import java.util.List;

import model.Sintomas;

public interface SintomasDao {

	public void save(Sintomas sintomas);

	public List<Sintomas> list();
	
	public void remove(Sintomas sintomas);

	public void update(Sintomas sintomas);
	
	public List<Sintomas> pesquisar(String NOME_SINTOMAS);
}
