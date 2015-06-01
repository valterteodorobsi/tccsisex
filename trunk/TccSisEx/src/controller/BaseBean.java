package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Usuario;


@ManagedBean
@SessionScoped
public class BaseBean{
	
	protected final String MENSAGEM_SUCESSO = "Dados salvos com sucesso!";

	protected final String MENSAGEM_FALHA_CARREGAR = "Falha ao carregar dados!";

	protected final String MENSAGEM_PRENCHER_CAMPOS = "Preencha todos os campos!";

	protected final String DADOS_INCORRETOS = "Os dados informados não são válidos!";

	protected final String NULL = null;
	
	protected Usuario usuarioLogado;
	
	protected Boolean campoPreenchido(Object obj){
		if(obj == null){
			return false;
		}
		
		try{
			String ob = (String) obj;
			if(ob.isEmpty()){
				return false;
			}
		}catch(Exception e){
			
		}
		
		return true;
		
	}
	
}
