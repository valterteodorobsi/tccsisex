package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.RegistroEntrada;
import dao.RegistroEntradaDao;
import dao.RegistroEntradaDaoImp;



@ManagedBean(name = "registroBen")
@ViewScoped
public class RegistroEntradaController {
		
	private RegistroEntrada registro;
	
	private List<RegistroEntrada> listaRegistro= null;
	
	

	
	

	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {

		registro = new RegistroEntrada();

	}

	
	
	public RegistroEntrada getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroEntrada registro) {
		this.registro = registro;
	}

	public List<RegistroEntrada> getListaRegistro() {
		if(listaRegistro == null){
			listaRegistro = new RegistroEntradaDaoImp().pesquisarTodos();
			
		}
		return listaRegistro;
	}

	public void setListaRegistro(List<RegistroEntrada> listaRegistro) {
		this.listaRegistro = listaRegistro;
	}
	
	
	
	public String adicionarRegistro() throws IOException {
		RegistroEntradaDao dao = new RegistroEntradaDaoImp();
		dao.save(registro);
		info();
		FacesContext.getCurrentInstance().getExternalContext().redirect("registraentrada.jsf");
		return "";
	}
	
	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Registrado com Sucesso. "));
	}
	public void warn() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
						"Nenhum registro foi encontrado. "));
	}
	
	public void pesquisar() {
		Integer matricula = registro.getID_MATRICULA();

		listaRegistro = new RegistroEntradaDaoImp().pesquisar(matricula);
		if (listaRegistro.isEmpty()) {
			warn();

		}
	}
	public void registrarSaida(RegistroEntrada registro){
		//RegistroEntrada registro = (RegistroEntrada) event.getObject();
		Integer id_matricula = registro.getID_MATRICULA();
		new RegistroEntradaDaoImp().update(id_matricula);

		infoAlterar();
		listaRegistro = null;
	}
	
	public void infoAlterar() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Registro de saida realizado com Sucesso. "));
	}
	
	
}
