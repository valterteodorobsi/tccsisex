package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.RelatorioPermanencia;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import dao.RelatorioPermanenciaDao;

@ManagedBean(name = "permanenciaBean")
@ViewScoped
public class RelatorioPermanenciaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RelatorioPermanencia permanencia;
	private List<RelatorioPermanencia> listaPermanencia = new ArrayList<RelatorioPermanencia>();
	
	private String path; // Caminho base
	private String pathToReportPackage; // Caminho para o package onde est�o
										// armazenados os relatorios Jarper

	public RelatorioPermanenciaController() {
		permanencia = new RelatorioPermanencia();
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jasper/";
		System.out.println(path);
	}

	public void imprimir() throws Exception {
		RelatorioPermanenciaDao dao = new RelatorioPermanenciaDao();
		String nomeFuncionario = permanencia.getNomeFuncionario();
		Date dataInicial = permanencia.getDataInicial();
		Date dataFinal = permanencia.getDataFinal();
		
		if(!dataFinal.after(dataInicial)){
			informacao();
		}else{
		if (permanencia.getNomeFuncionario().equals("todos")) {

			listaPermanencia = dao.RelatorioPermanenciaTodos(nomeFuncionario, dataInicial, dataFinal);
		} else {

			listaPermanencia = dao.RelatorioPermanencia(nomeFuncionario, dataInicial, dataFinal);
		}

		List<RelatorioPermanencia> relatorioPermanencia = new ArrayList<RelatorioPermanencia>();

		for (RelatorioPermanencia permanencia : listaPermanencia) {
			permanencia.setDataInicial(dataInicial);
			permanencia.setDataFinal(dataFinal);
			relatorioPermanencia.add(permanencia);
		}
		
		
		if (listaPermanencia.isEmpty()) {
			semRegistro();
		} else {
			JasperReport report = JasperCompileManager.compileReport(this
					.getPathToReportPackage() + "Relatorio_Permanencia.jrxml");

			JasperPrint print = JasperFillManager.fillReport(report, null,
					new JRBeanCollectionDataSource(relatorioPermanencia));
			// abre visualizador
			JasperViewer jv = new JasperViewer(print, false);
			jv.setTitle("Relatorio Tempo M�dio de Permanencia");
			jv.setVisible(true);

			JasperExportManager.exportReportToPdfFile(print,
					"c:/relatorio/Relatorio_Permanencia.pdf");

			}
		}
	}

	public RelatorioPermanencia getPermanencia() {
		return permanencia;
	}

	public void setPermanencia(RelatorioPermanencia permanencia) {
		this.permanencia = permanencia;
	}

	public List<RelatorioPermanencia> getListaPermanencia() {
		return listaPermanencia;

	}

	public void setListaPermanencia(List<RelatorioPermanencia> listaPermanencia) {
		this.listaPermanencia = listaPermanencia;
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

	public void semRegistro() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
						"N�o existe registros"));
	}
	
	public void informacao() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
						"A data final n�o pode ser menor que a data inicial. "));
	}
	
}
