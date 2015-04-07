package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.RelatorioAtestado;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.primefaces.model.chart.PieChartModel;

import dao.RelatorioAtestadosDao;

@ManagedBean(name = "RelAtestadosBean")
@ViewScoped
public class RelatorioAtestadoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RelatorioAtestado relAtestados;
	private List<RelatorioAtestado> listaRelAtestados= new ArrayList<RelatorioAtestado>();
	private Date dataInicial;
	private Date dataFinal;
	private String path; // Caminho base
	private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jarper
	private PieChartModel piechart;
	private Date Time;
	
	
	public RelatorioAtestadoController(){
		relAtestados = new RelatorioAtestado();
		piechart = new PieChartModel();
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jasper/";
		System.out.println(path);
		
	}
	
	
	public void imprimir() throws Exception {
		RelatorioAtestadosDao dao = new RelatorioAtestadosDao();
        String nomeColaborador = relAtestados.getNomeColaborador();
        String nomeSetor = relAtestados.getNomeSetor();
        
        
        if(relAtestados.getNomeSetor().equals("todos")){
        	
        	listaRelAtestados =  dao.relatorioAtestadoTodos(nomeColaborador, nomeSetor);
			
        	List<RelatorioAtestado> relatorioAtestados = new ArrayList<RelatorioAtestado>();

			for (RelatorioAtestado relAtesRelatorioAtestado : listaRelAtestados) {

				relatorioAtestados.add(relAtesRelatorioAtestado);

			}

			JasperReport report = JasperCompileManager.compileReport(this
					.getPathToReportPackage()
					+ "Relatorio_Atestados.jrxml");

			JasperPrint print = JasperFillManager.fillReport(report, null,
					new JRBeanCollectionDataSource(relatorioAtestados));
			// abre visualizador
			Date Tim = new Date();
			long tim = Tim.getTime();
			Time.setTime(tim);
			JasperViewer jv = new JasperViewer(print, false);
			jv.setTitle("Relatorio Atestados");
			jv.setVisible(true);

			JasperExportManager.exportReportToPdfFile(print,
					"c:/relatorio/Relatorio_Atestados.pdf");
		
        }
        else{
              
        listaRelAtestados =  dao.RelatorioAtestado(nomeColaborador, nomeSetor);
			
			List<RelatorioAtestado> relatorioAtestados = new ArrayList<RelatorioAtestado>();

			for (RelatorioAtestado relAtesRelatorioAtestado: listaRelAtestados) {

				relatorioAtestados.add(relAtesRelatorioAtestado);

			}

			JasperReport report = JasperCompileManager.compileReport(this
					.getPathToReportPackage()
					+ "Relatorio_Atestados_unitario.jrxml");

			JasperPrint print = JasperFillManager.fillReport(report, null,
					new JRBeanCollectionDataSource(relatorioAtestados));
			// abre visualizador
			JasperViewer jv = new JasperViewer(print, false);
			jv.setTitle("Relatorio Atestados");
			jv.setVisible(true);

			JasperExportManager.exportReportToPdfFile(print,
					"c:/relatorio/Relatorio_Atestados.pdf");
		}
        
	}
	
	
	
	public void warn() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
						"Não é possivel gerar o grafico com o Valor 'TODOS'. "));
	}
	
	
	public RelatorioAtestado getRelAtestados() {
		return relAtestados;
	}
	public void setRelAtestados(RelatorioAtestado relAtestados) {
		this.relAtestados = relAtestados;
	}
	public List<RelatorioAtestado> getListaRelAtestados() {
		return listaRelAtestados;
	}
	public void setListaRelAtestados(List<RelatorioAtestado> listaRelAtestados) {
		this.listaRelAtestados = listaRelAtestados;
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


	public Date getTime() {
		return Time;
	}


	public void setTime(Date time) {
		Time = time;
	}
	
	

}
