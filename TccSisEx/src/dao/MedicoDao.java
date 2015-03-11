package dao;

import model.Medico;
import java.util.List;

public interface MedicoDao {

	public void save(Medico medico);

	public List<Medico> list();

	public void remove(Medico medico);

	public void update(Medico medico);
	
	public List<Medico> pesquisar(int matricula);
	
	public List<Medico> pesquisarNome(String nome);
	

}