package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.sun.org.apache.bcel.internal.generic.Select;

import antlr.debug.Event;
import model.Funcao;
import model.RegistroEntrada;
import dao.FuncaoDaoImp;
import dao.RegistroEntradaDao;
import dao.RegistroEntradaDaoImp;



@ManagedBean(name = "registroBen")
@ViewScoped
public class RegistroEntradaController {
		
	private RegistroEntrada registro;
	
	private List<RegistroEntrada> listaRegistro= new ArrayList<RegistroEntrada>();
	
	

	
	

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
		return listaRegistro;
	}

	public void setListaRegistro(List<RegistroEntrada> listaRegistro) {
		this.listaRegistro = listaRegistro;
	}
	
	
	
	public String adicionarRegistro() {
		RegistroEntradaDao dao = new RegistroEntradaDaoImp();
		dao.save(registro);
		info();
		return "/home.jsf";
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
		if (matricula == 0 || matricula == null) {
			warn();

		}
	}
	public void registrarSaida(RegistroEntrada registro){
		//RegistroEntrada registro = (RegistroEntrada) event.getObject();
		Integer id_matricula = registro.getID_MATRICULA();
		new RegistroEntradaDaoImp().update(id_matricula);

		infoAlterar();
	}
	
	public void infoAlterar() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Registro de saida realizado com Sucesso. "));
	}
	
	
}
