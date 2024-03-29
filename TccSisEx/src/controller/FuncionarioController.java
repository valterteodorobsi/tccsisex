package controller;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Funcionario;
import model.FuncionarioPes;

import org.hibernate.HibernateException;
import org.primefaces.event.RowEditEvent;

import dao.FuncionarioDao;
import dao.FuncionarioDaoImp;

@ManagedBean(name="funcionarioBen")
@ViewScoped
public class FuncionarioController {

	private Funcionario funcionario = new Funcionario();


	private List<FuncionarioPes> listaFuncionariosPes = null;
	

	public List<FuncionarioPes> getListaFuncionariosPes() {
		if(listaFuncionariosPes == null){
			listaFuncionariosPes = new FuncionarioDaoImp().pesquisarVazio();
		}
		
		return listaFuncionariosPes;
	}

	public void setListaFuncionariosPes(List<FuncionarioPes> listaFuncionariosPes) {
		this.listaFuncionariosPes = listaFuncionariosPes;
	}



	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void excluirFuncionario(Funcionario funcionario) {

		new FuncionarioDaoImp().remove(funcionario);
		infoExcluir();
		pesquisarFun();
		
		
	}

	public String adicionarFuncionario() throws HibernateException, Exception {
		FuncionarioDao dao = new FuncionarioDaoImp();
		if(!validarEmail(funcionario.getEMAIL())){
			return "";
			
		}
		dao.save(funcionario);
		info();
		listaFuncionariosPes = null;
		FacesContext.getCurrentInstance().getExternalContext().redirect("funcionario.jsf");
		
		return "";
	}

	public void pesquisarFun() {
		Integer matricula = funcionario.getID_MATRICULA();
		String nome = funcionario.getNOME();
		if(matricula == 0){
		
			listaFuncionariosPes = new FuncionarioDaoImp().pesquisarNome(nome);
				if (listaFuncionariosPes.isEmpty()) {
				warn();
				}
			}else{
			listaFuncionariosPes = new FuncionarioDaoImp().pesquisar(matricula);
			if (listaFuncionariosPes.isEmpty()) {
				warn();

			}
		}
		

	}
	
	public List listaColaborador(){

		List nomeFuncionario = new FuncionarioDaoImp().listaNome(funcionario.getID_MATRICULA());
		
		if(!nomeFuncionario.isEmpty()) {
			for(int i=0; i<nomeFuncionario.size();i++) {
				funcionario.setNOME((String)nomeFuncionario.get(0));
				//funcionario.setNOMESET((String) nomeFuncionario.get(0));
			}
		} else {
		warn();
		}
		return nomeFuncionario;
		}
	
	
	public List listaColaboradorComboBox(){
		
		List nomeFuncionario = new FuncionarioDaoImp().list();
		
		return nomeFuncionario ;
		
	}

	public List listaColaboradorProntuarios() {

		List<FuncionarioPes> nomeFuncionario =  new FuncionarioDaoImp()
				.listaColaboradorProntuario(funcionario.getID_MATRICULA());

		if (!nomeFuncionario.isEmpty()) {
			
			for (int i = 0; i < nomeFuncionario.size(); i++) {
				funcionario.setNOME(nomeFuncionario.get(0).getNOME().toString());
				funcionario.setNOMEFUN(nomeFuncionario.get(0).getNOMEFUN().toString());
				funcionario.setNOMESET(nomeFuncionario.get(0).getNOMESET().toString());
				
			}
		} else {
			warn();
		}
		return nomeFuncionario;
	}
	public List listaColaboradorAnexo() {

		List<FuncionarioPes> nomeFuncionario =  new FuncionarioDaoImp()
				.listaColaboradorProntuario(funcionario.getID_MATRICULA());

		if (!nomeFuncionario.isEmpty()) {
			
			for (int i = 0; i < nomeFuncionario.size(); i++) {
				funcionario.setNOME(nomeFuncionario.get(0).getNOME().toString());
				funcionario.setNOMESET(nomeFuncionario.get(0).getNOMESET().toString());
				funcionario.setID_CENTRO_CUSTO(nomeFuncionario.get(0).getID_CENTRO_CUSTO());
				
			}
		} else {
			warn();
		}
		return nomeFuncionario;
	}
	
	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Colaborador Cadastrado com Sucesso. "));
	}

	public void infoExcluir() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Colaborador foi exclu�do com sucesso. "));
	}

	public static void warn() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
						"Nenhum registro foi encontrado. "));
	}

	public void infoAlterar() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Colaborador Alterado com Sucesso. "));
	}
	public static void erroData() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data de Nascimento invalida ",
						""));
	}


	public void onRowEdit(RowEditEvent event) {

		Funcionario funcionario = (Funcionario) event.getObject();

		new FuncionarioDaoImp().update(funcionario);
		infoAlterar();
		pesquisarFun();
		
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edi��o Cancelada");
		FacesContext.getCurrentInstance().addMessage("Edi��o Cancelada", msg);
	}
	
	public static void matriculaErro() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
						"Matricula j� existente. "));
	}
	
	public List<FuncionarioPes> listaFuncionarios(){
		
			String nome = null;
			listaFuncionariosPes = new FuncionarioDaoImp().pesquisarNome(nome);
				
			
		
		return listaFuncionariosPes;
	}
	
	public List listaColaboradorPedido() {

		List<FuncionarioPes> nomeFuncionario =  new FuncionarioDaoImp()
		.listaColaboradorPedido(funcionario.getID_MATRICULA());
		
		if (!nomeFuncionario.isEmpty()) {
			for (int i = 0; i < nomeFuncionario.size(); i++) {
				funcionario.setNOME(nomeFuncionario.get(0).getNOME().toString());
				funcionario.setNOMEFUN(nomeFuncionario.get(0).getNOMEFUN().toString());
				funcionario.setNOMESET(nomeFuncionario.get(0).getNOMESET().toString());
				funcionario.setDATA_NASC(new Date(nomeFuncionario.get(0).getDATA_NASC().getTime()));
				
			}
		} else {
			warn();
		}
		return nomeFuncionario;
	}
	
	 public boolean validarEmail(String email) {
	        Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");

	        if (email != null && !email.equals("")) {
	            if (!pattern.matcher(email.toString()).matches()) {

	            	FacesContext.getCurrentInstance().addMessage(
	        				null,
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
	        						"E-mail invalido"));
	                return false;
	            }

	           
	        }
	        return true;

	    }
	
} 

