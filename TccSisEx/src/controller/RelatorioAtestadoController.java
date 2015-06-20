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
	
	private String path; // Caminho base
	private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jarper
	
	
	public RelatorioAtestadoController(){
		relAtestados = new RelatorioAtestado();
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jasper/";
		System.out.println(path);
		
	}
	
	
	public void imprimir() throws Exception {
		RelatorioAtestadosDao dao = new RelatorioAtestadosDao();
        Integer nomeColaborador = relAtestados.getIdColaborador();
        Integer nomeSetor = relAtestados.getIdSetor();
        Date dataInicial = relAtestados.getDataInicial();
        Date dataFinal = relAtestados.getDataFinal();
        
        if(nomeColaborador.equals(0)){
        	
        	listaRelAtestados =  dao.relatorioAtestadoTodos(nomeColaborador, nomeSetor, dataInicial, dataFinal );
			
        	List<RelatorioAtestado> relatorioAtestados = new ArrayList<RelatorioAtestado>();

			for (RelatorioAtestado relAtesRelatorioAtestado : listaRelAtestados) {
				relAtesRelatorioAtestado.setDataInicial(dataInicial);
				relAtesRelatorioAtestado.setDataFinal(dataFinal);
				relatorioAtestados.add(relAtesRelatorioAtestado);

			}
			if (relatorioAtestados.isEmpty()){
				warn();
			}else{
			JasperReport report = JasperCompileManager.compileReport(this
					.getPathToReportPackage()
					+ "Relatorio_Atestados.jrxml");

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
        else{
              
        listaRelAtestados =  dao.RelatorioAtestado(nomeColaborador, nomeSetor, dataInicial, dataFinal);
			
			List<RelatorioAtestado> relatorioAtestados = new ArrayList<RelatorioAtestado>();

			for (RelatorioAtestado relAtesRelatorioAtestado: listaRelAtestados) {
				relAtesRelatorioAtestado.setDataInicial(dataInicial);
				relAtesRelatorioAtestado.setDataFinal(dataFinal);
				relatorioAtestados.add(relAtesRelatorioAtestado);

			}
			if(relatorioAtestados.isEmpty()){
				warn();
			}else{
			
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
	}
	
	
	
	public void warn() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
						"Nenhum registro foi encontrado.  "));
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


	
	

}
