package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.RelatorioSintomas;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.primefaces.model.chart.PieChartModel;

import dao.RelatorioSintomasDao;

@ManagedBean(name = "RelSintomasBean")
@ViewScoped
public class RelatorioSintomasController  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RelatorioSintomas relSintomas;
	private List<RelatorioSintomas> listaRelSintomas= new ArrayList<RelatorioSintomas>();
	private Date dataInicial;
	private Date dataFinal;
	private String path; // Caminho base
	private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jarper
	private PieChartModel piechart;
	
	
	public RelatorioSintomasController() {
		relSintomas = new RelatorioSintomas();
		piechart = new PieChartModel();
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jasper/";
		System.out.println(path);
	}

	public void imprimir() throws Exception {
		RelatorioSintomasDao dao = new RelatorioSintomasDao();
        String nomeSintomas = relSintomas.getNomeSintomas();
        String nomeSetor = relSintomas.getNomeSetor();
        
        
        if(relSintomas.getNomeSetor().equals("todos")){
        	
        	listaRelSintomas =  dao.relatorioSintomasTodos(nomeSintomas , nomeSetor);
			
        	List<RelatorioSintomas> relatorioSintomas = new ArrayList<RelatorioSintomas>();

			for (RelatorioSintomas relSintomas : listaRelSintomas) {

				relatorioSintomas.add(relSintomas);

			}

			JasperReport report = JasperCompileManager.compileReport(this
					.getPathToReportPackage()
					+ "Relatorio_Sintomas.jrxml");

			JasperPrint print = JasperFillManager.fillReport(report, null,
					new JRBeanCollectionDataSource(relatorioSintomas));
			// abre visualizador
			JasperViewer jv = new JasperViewer(print, false);
			jv.setTitle("Relatorio Sintomas");
			jv.setVisible(true);

			JasperExportManager.exportReportToPdfFile(print,
					"c:/relatorio/Relatorio_Sintomas.pdf");
		
        }
        else{
              
        listaRelSintomas =  dao.relatorioSintomas(nomeSintomas, nomeSetor);
			
			List<RelatorioSintomas> relatorioSintomas = new ArrayList<RelatorioSintomas>();

			for (RelatorioSintomas relSintomas : listaRelSintomas) {

				relatorioSintomas.add(relSintomas);

			}

			JasperReport report = JasperCompileManager.compileReport(this
					.getPathToReportPackage()
					+ "Relatorio_Sintomas_unitario.jrxml");

			JasperPrint print = JasperFillManager.fillReport(report, null,
					new JRBeanCollectionDataSource(relatorioSintomas));
			// abre visualizador
			JasperViewer jv = new JasperViewer(print, false);
			jv.setTitle("Relatorio Sintomas");
			jv.setVisible(true);

			JasperExportManager.exportReportToPdfFile(print,
					"c:/relatorio/Relatorio_Sintomas.pdf");
		}
        
	}
	
	public void abreGrafico(){
		if(relSintomas.getNomeSetor().equals("todos") || relSintomas.getNomeSintomas().equals("todos")){
			warn();
		} 
		else if(relSintomas != null){
                
        piechart = new PieChartModel();
        RelatorioSintomasDao dao = new RelatorioSintomasDao();
        String nomeSintomas = relSintomas.getNomeSintomas();
        String nomeSetor = relSintomas.getNomeSetor();
       
        listaRelSintomas =  dao.relatorioSintomas(nomeSintomas, nomeSetor);
                
                piechart = new PieChartModel();
                
                for(RelatorioSintomas sintomas : listaRelSintomas){
                       // piechart.set(( Integer.valueOf(sintomas.getQtd_sintomas,sintomas.getNomeSintomas(), sintomas.getNomeSetor())));
                }
        }
        
}
	
	
	
	
	
	
	public void warn() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
						"Não é possivel gerar o grafico com o Valor 'TODOS'. "));
	}
	
	
	public RelatorioSintomas getRelSintomas() {
		return relSintomas;
	}
	public void setRelSintomas(RelatorioSintomas relSintomas) {
		this.relSintomas = relSintomas;
	}
	public List<RelatorioSintomas> getListaRelSintomas() {
		return listaRelSintomas;
	}
	public void setListaRelSintomas(List<RelatorioSintomas> listaRelSintomas) {
		this.listaRelSintomas = listaRelSintomas;
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
	public PieChartModel getPiechart() {
		return piechart;
	}
	public void setPiechart(PieChartModel piechart) {
		this.piechart = piechart;
	}	
	
	
	
	
	
}
