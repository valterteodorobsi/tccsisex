package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.RelatorioGerencial;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.primefaces.model.chart.PieChartModel;

import util.TipoEntrada;
import dao.RelatorioGerencialDao;

@ManagedBean(name = "gerencialBean")
@ViewScoped
public class RelatorioGerencialController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RelatorioGerencial gerencial;
	private List<RelatorioGerencial> listaGerencial = new ArrayList<RelatorioGerencial>();
	private Date dataInicial;
	private Date dataFinal;
	private String path; // Caminho base
	private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jarper
	private PieChartModel piechart;								 
	private Date Time;
	public RelatorioGerencialController() {
		gerencial = new RelatorioGerencial();
		piechart = new PieChartModel();
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jasper/";
		System.out.println(path);
	}

	public void imprimir() throws Exception {
		RelatorioGerencialDao dao = new RelatorioGerencialDao();
        String nomeMedico = gerencial.getNomeMedico();
        
        if(gerencial.getNomeMedico().equals("todos")){
        	
        	listaGerencial =  dao.relatorioGerencialTodos(nomeMedico);
        }
        else{
              
        listaGerencial =  dao.relatorioGerencial(nomeMedico);
		}
        
        List<RelatorioGerencial> relatorioGerencial = new ArrayList<RelatorioGerencial>();
        
        for(RelatorioGerencial gerencial : listaGerencial){
        	if(gerencial.getTipoEntrada().equals("0") ){
        		gerencial.setTipoEntrada("Emergencial");
        	relatorioGerencial.add(gerencial);
        	}else{
        		gerencial.setTipoEntrada("Eletivo");
        		relatorioGerencial.add(gerencial);
        	}
    }
        
       
		JasperReport report = JasperCompileManager.compileReport(this
				.getPathToReportPackage() + "Relatorio_Gerencial.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(relatorioGerencial));
		// abre visualizador
		Date Tim = new Date();
		long tim = Tim.getTime();
		Time.setTime(tim);
		JasperViewer jv = new JasperViewer(print, false);
		jv.setTitle("Relatorio Gerencial");
		jv.setVisible(true);

		JasperExportManager.exportReportToPdfFile(print,
				"c:/relatorio/Relatorio_Gerencial.pdf");
	}
	
	public void abreGrafico(){
		if(gerencial.getNomeMedico().equals("todos")){
			warn();
		} 
		else if(gerencial != null){
                
        piechart = new PieChartModel();
        RelatorioGerencialDao dao = new RelatorioGerencialDao();
        String nomeMedico = gerencial.getNomeMedico();
       
        listaGerencial =  dao.relatorioGerencial(nomeMedico);
                
                piechart = new PieChartModel();
                
                for(RelatorioGerencial gerencial : listaGerencial){
                        piechart.set(TipoEntrada.getByOrdinal( Integer.valueOf(gerencial.getTipoEntrada())).getDescricao(), gerencial.getQtdeEntrada());
                }
        }
        
}
	public void warn() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
						"Não é possivel gerar o grafico com o Valor 'TODOS'. "));
	}
	
	public PieChartModel getPiechart() {
		return piechart;
	}

	public void setPiechart(PieChartModel piechart) {
		this.piechart = piechart;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPathToReportPackage() {
		return pathToReportPackage;
	}

	public void setPathToReportPackage(String pathToReportPackage) {
		this.pathToReportPackage = pathToReportPackage;
	}

	public RelatorioGerencial getGerencial() {
		return gerencial;
	}

	public void setGerencial(RelatorioGerencial gerencial) {
		this.gerencial = gerencial;
	}

	public List<RelatorioGerencial> getListaGerencial() {
		return listaGerencial;
	}

	public void setListaGerencial(List<RelatorioGerencial> listaGerencial) {
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

	public Date getTime() {
		return Time;
	}

	public void setTime(Date time) {
		Time = time;
	}
	
}
