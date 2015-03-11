package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Remedio;
import dao.RemedioDao;



/*essa clase favor nao considera como a tela 
pois utilizo ela somente para acessar uma tebela no banco onde contem os remedios pré cadastrados que ele devem ter já
SOMENETE UTILIZEI PARA TRAZER NA COMBOBOX OS DADOS DO BANCO*/

@ManagedBean(name = "remedioBen")
@ViewScoped
public class RemedioController {
	
	private Remedio remedio;



	private List<Remedio> listaRemedio = new ArrayList<Remedio>();
	
	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {

		remedio = new Remedio();
		
	}
	
	
	public Remedio getRemedio() {
		return remedio;
	}

	public void setRemedio(Remedio remedio) {
		this.remedio = remedio;
	}
	
	public List<Remedio> getListaRemedio() {
		return listaRemedio;
	}

	public void setListaRemedio(List<Remedio> listaRemedio) {
		this.listaRemedio = listaRemedio;
	}
	
	
	public List<Remedio> listaRemedio(){
		listaRemedio = new RemedioDao().list();
		
		
		return listaRemedio;
		
	}
}
