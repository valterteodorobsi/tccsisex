package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Medico;
import dao.MedicoDaoImp;

@ManagedBean(name = "gerencialBen")
@ViewScoped
public class RelatorioGerencialController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Medico medico;
	private List<Medico> listMedico;

	public RelatorioGerencialController() {
		
		listMedico = new MedicoDaoImp().list();
	} 
		
	
	
	
	
	
	public Medico getMedico() {
		return medico;
	}


	public void setMedico(Medico medico) {
		this.medico = medico;
	}


	public List<Medico> getListMedico() {
		return listMedico;
	}


	public void setListMedico(List<Medico> listMedico) {
		this.listMedico = listMedico;
	}
	
	



}
