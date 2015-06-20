package dao;

import java.util.List;

import model.Usuario;

public interface UsuarioDao {
	public List<Usuario> carregarUsuarioLoginSenha(Usuario usuario) throws Exception;
	
	public void salvar(Usuario usuario) throws Exception;
}
