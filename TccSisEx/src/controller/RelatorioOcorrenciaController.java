package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.RelatorioOcorrencia;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.primefaces.model.chart.PieChartModel;

import dao.RelatorioOcorrenciaDao;

@ManagedBean(name = "RelOcorrenciaBean")
@ViewScoped
public class RelatorioOcorrenciaController {

	private RelatorioOcorrencia relOcorrencias;
	private List<RelatorioOcorrencia> listaRelOcorrencias = new ArrayList<RelatorioOcorrencia>();
	private String path; // Caminho base
	private String pathToReportPackage; // Caminho para o package onde est�o
										// armazenados os relatorios Jarper
	private PieChartModel piechart;

	public RelatorioOcorrenciaController() {
		relOcorrencias = new RelatorioOcorrencia();
		piechart = new PieChartModel();
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jasper/";
		System.out.println(path);

	}

	public void imprimir() throws Exception {
		RelatorioOcorrenciaDao dao = new RelatorioOcorrenciaDao();
		Integer nomeColaborador = relOcorrencias.getIdMatricula();
		Integer nomeSetor = relOcorrencias.getIdCentroCusto();
		Date dataInicial = relOcorrencias.getDataInicial();
		Date dataFinal = relOcorrencias.getDataFinal();

		if (!dataFinal.after(dataInicial)) {
			warn();
		} else {
			if (relOcorrencias.getIdCentroCusto().equals(0)) {

				listaRelOcorrencias = dao.relatorioOcorrenciaTodos(
						nomeColaborador, nomeSetor, dataInicial, dataFinal);

				List<RelatorioOcorrencia> relatorioOcorrencias = new ArrayList<RelatorioOcorrencia>();

				for (RelatorioOcorrencia relRelatorioOcorrencia : listaRelOcorrencias) {
					relRelatorioOcorrencia.setDataFinal(dataFinal);
					relRelatorioOcorrencia.setDataInicial(dataInicial);
					relatorioOcorrencias.add(relRelatorioOcorrencia);

				}
				if (relatorioOcorrencias.isEmpty()) {
					semRegistro();
				} else {

					JasperReport report = JasperCompileManager
							.compileReport(this.getPathToReportPackage()
									+ "Relatorio_Ocorrencias.jrxml");

					JasperPrint print = JasperFillManager.fillReport(report,
							null, new JRBeanCollectionDataSource(
									relatorioOcorrencias));
					// abre visualizador
					JasperViewer jv = new JasperViewer(print, false);
					jv.setTitle("Relatorio Ocorrencias");
					jv.setVisible(true);

					JasperExportManager.exportReportToPdfFile(print,
							"c:/relatorio/Relatorio_Ocorrencias.pdf");
				}
			} else {

				listaRelOcorrencias = dao.relatorioOcorrencia(nomeColaborador,
						nomeSetor, dataInicial, dataFinal);

				List<RelatorioOcorrencia> relatorioOcorrencias = new ArrayList<RelatorioOcorrencia>();

				for (RelatorioOcorrencia relAtesRelatorioOcorrencia : listaRelOcorrencias) {
					relAtesRelatorioOcorrencia.setDataFinal(dataFinal);
					relAtesRelatorioOcorrencia.setDataInicial(dataInicial);
					relatorioOcorrencias.add(relAtesRelatorioOcorrencia);

				}
				if (relatorioOcorrencias.isEmpty()) {
					semRegistro();
				} else {
					JasperReport report = JasperCompileManager
							.compileReport(this.getPathToReportPackage()
									+ "Relatorio_Ocorrencias_unitario.jrxml");

					JasperPrint print = JasperFillManager.fillReport(report,
							null, new JRBeanCollectionDataSource(
									relatorioOcorrencias));
					// abre visualizador
					JasperViewer jv = new JasperViewer(print, false);
					jv.setTitle("Relatorio Ocorrencias");
					jv.setVisible(true);

					JasperExportManager.exportReportToPdfFile(print,
							"c:/relatorio/Relatorio_Ocorrencias.pdf");
				}
			}
		}
	}

	public void warn() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
								"A data final n�o pode ser menor que a data inicial. "));
	}

	public void semRegistro() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
						"N�o existe registros"));
	}

	public RelatorioOcorrencia getRelOcorrencias() {
		return relOcorrencias;
	}

	public void setRelOcorrencias(RelatorioOcorrencia relOcorrencias) {
		this.relOcorrencias = relOcorrencias;
	}

	public List<RelatorioOcorrencia> getListaRelOcorrencias() {
		return listaRelOcorrencias;
	}

	public void setListaRelOcorrencias(
			List<RelatorioOcorrencia> listaRelOcorrencias) {
		this.listaRelOcorrencias = listaRelOcorrencias;
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
