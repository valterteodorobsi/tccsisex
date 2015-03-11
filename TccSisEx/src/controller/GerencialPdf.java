package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Gerencial;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import dao.GerencialDao;

@ManagedBean(name = "gerencialPDFBean")
@ViewScoped
public class GerencialPdf implements Serializable {
///Estou vendo essa clase
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gerencial gerencial1 ;
	private List<Gerencial> listaGerencial = new ArrayList<Gerencial>();
	private Date dataInicial;
	private Date dataFinal;
	
	
	
	
	 
	public GerencialPdf () {
		
		
		gerencial1 = new Gerencial();
		String nomeMedico = gerencial1.getNomeMedico();
		GerencialDao dao = new GerencialDao();
		listaGerencial =  dao.relatorioGerencial(nomeMedico);
	  
	
	for(Gerencial gerencial : listaGerencial){
		//piechart.set(TipoEntrada.getByOrdinal( Integer.valueOf(gerencial1.getTipoEntrada())).getDescricao(), gerencial1.getQtdeEntrada());
		listaGerencial.addAll(getListaGerencial());
	}
	
	}
	
	  
	// compilacao do JRXML 
	
	//JasperReport report = JasperCompileManager .compileReport("/Relatorio_Gerencial.jrxml"); 
	// preenchimento do relatorio, note que o metodo recebe 3 parametros: 
	// 1 - o relatorio
	// 
	// 2 - um Map, com parametros que sao passados ao relatorio 
	// no momento do preenchimento. No nosso caso eh null, pois nao 
	// estamos usando nenhum parametro 
	//
	// 3 - o data source. Note que nao devemos passar a lista diretamente, 
	// e sim "transformar" em um data source utilizando a classe 
	// JRBeanCollectionDataSource 
	//JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaGerencial)); 
	// exportacao do relatorio para outro formato, no caso PDF 
	//JasperExportManager.exportReportToPdfFile(print , "Relatorio_Gerencial.pdf"); 
	
	 
	
	
	
	public Gerencial getGerencial() {
		return gerencial1;
	}
	public void setGerencial(Gerencial gerencial) {
		this.gerencial1 = gerencial;
	}
	public List<Gerencial> getListaGerencial() {
		return listaGerencial;
	}
	public void setListaGerencial(List<Gerencial> listaGerencial) {
		this.listaGerencial = listaGerencial;
	}
	

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}


}
	

