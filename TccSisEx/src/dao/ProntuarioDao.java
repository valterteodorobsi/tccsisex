package dao;

import java.util.List;

import model.Prontuario;

public interface ProntuarioDao {
	public void save(Prontuario prontuario);

	public Prontuario getExame(int id);

	public List<Prontuario> list();

	public void remove(Prontuario prontuario);

	public void update(Prontuario prontuario);
	
	/*public List<Prontuario> pesquisarPorMatriculaOuNome(Integer id_matricula, String nome);*/
	public List<Prontuario> pesquisarPorMatricula(Integer id_matricula);
}
