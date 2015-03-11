package dao;

import java.util.List;

import model.RegistroEntrada;

public interface RegistroEntradaDao {
	
	public void save(RegistroEntrada registro);
	
	public List<RegistroEntrada> list() ;
	
	public List<RegistroEntrada> pesquisar(Integer ID_MATRICULA) ;
	
}
