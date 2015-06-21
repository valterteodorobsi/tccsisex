package dao;

import java.util.List;

import model.Usuario;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class UsuarioImpl implements UsuarioDao {

	public List<Usuario> usuarioList(String login, String senha) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		System.out.println("Pesquisando usuario Login: "+login+" Senha:" + senha);
		
		return this.carregarUsuarioLoginSenha(usuario);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> carregarUsuarioLoginSenha(Usuario usuario) throws Exception {
		if(usuario.getLogin() != null && !usuario.getLogin().isEmpty()
		&& usuario.getSenha() != null && !usuario.getSenha().isEmpty()){
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			SQLQuery query  = session.createSQLQuery("SELECT nome, nivel_usuario FROM Usuario as usuario WHERE usuario.usuario = :login AND usuario.senha = :senha ");
			query.setParameter("login", usuario.getLogin());
			query.setParameter("senha", usuario.getSenha());
			query.addScalar("nome");
			query.addScalar("nivel_Usuario");
			
			query.setResultTransformer( Transformers.aliasToBean( Usuario.class ) );
			 List<Usuario> users = query.list();

				
				return users;
		}
		return null;
	}
	
	public void salvar(Usuario usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery("insert into USUARIO (USUARIO, SENHA, NIVEL_USUARIO, NOME)" +
				" values( :USUARIO, :SENHA, :NIVEL_USUARIO,:NOME)");
				query.setParameter("USUARIO", usuario.getLogin());
				query.setParameter("SENHA", usuario.getSenha());
				query.setParameter("NIVEL_USUARIO", usuario.getnivel_Usuario());
				query.setParameter("NOME", usuario.getNome()); 
				  
			query.executeUpdate();
				t.commit();
	}
}
