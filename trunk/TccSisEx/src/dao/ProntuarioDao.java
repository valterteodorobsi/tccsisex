package dao;

import java.util.List;

import model.Prontuario;

import org.hibernate.HibernateException;

public interface ProntuarioDao {
	public void save(Prontuario prontuario)throws HibernateException, Exception;

	public List<Prontuario> list();

	public void remove(Prontuario prontuario);

	public void update(Prontuario prontuario);
	
	/*public List<Prontuario> pesquisarPorMatriculaOuNome(Integer id_matricula, String nome);*/
	public List<Prontuario> pesquisarPorMatricula(Integer id_matricula);
	
	public List<Prontuario> pesquisarNome();
}
