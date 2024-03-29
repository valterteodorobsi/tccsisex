package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.RelatorioResponsabilidades;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import dao.RelatorioResponsabilidadesDao;

@ManagedBean(name = "responsabilidadesBean")
@ViewScoped
public class RelatorioResponsabilidadesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RelatorioResponsabilidades resposabilidades;
	private List<RelatorioResponsabilidades> listaReposnsabilidades = new ArrayList<RelatorioResponsabilidades>();
	private String path; // Caminho base
	private String pathToReportPackage; // Caminho para o package onde est�o
										// armazenados os relatorios Jarper

	public RelatorioResponsabilidadesController() {
		resposabilidades = new RelatorioResponsabilidades();
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jasper/";
	}

	public void imprimir() throws Exception {
		RelatorioResponsabilidadesDao dao = new RelatorioResponsabilidadesDao();
		String nomeSetor = resposabilidades.getNomeSetor();
		Date dataInicial = resposabilidades.getDataInicial();
		Date dataFinal = resposabilidades.getDataFinal();

		if (!dataFinal.after(dataInicial)) {
			informacao();
		} else {
			if (resposabilidades.getNomeSetor().equals("todos")) {

				listaReposnsabilidades = dao
						.RelatorioResponsabilidadesTodos(nomeSetor);
			} else {

				listaReposnsabilidades = dao
						.RelatorioResponsabilidades(nomeSetor);
			}

			List<RelatorioResponsabilidades> relatorioResponsabilidade = new ArrayList<RelatorioResponsabilidades>();

			for (RelatorioResponsabilidades responsabilidades : listaReposnsabilidades) {
				responsabilidades.setDataInicial(dataInicial);
				responsabilidades.setDataFinal(dataFinal);
				relatorioResponsabilidade.add(responsabilidades);

			}
			if (listaReposnsabilidades.isEmpty()) {

				semRegistro();
			} else {

				JasperReport report = JasperCompileManager.compileReport(this
						.getPathToReportPackage()
						+ "Relatorio_Responsabilidades.jrxml");

				JasperPrint print = JasperFillManager.fillReport(report, null,
						new JRBeanCollectionDataSource(
								relatorioResponsabilidade));
				// abre visualizador
				JasperViewer jv = new JasperViewer(print, false);
				jv.setTitle("Relatorio De Responsabilidades");
				jv.setVisible(true);

				JasperExportManager.exportReportToPdfFile(print,
						"c:/relatorio/Relatorio_Responsabilidades.pdf");

			}
		}
	}

	public RelatorioResponsabilidades getResposabilidades() {
		return resposabilidades;
	}

	public void setResposabilidades(RelatorioResponsabilidades resposabilidades) {
		this.resposabilidades = resposabilidades;
	}

	public List<RelatorioResponsabilidades> getListaReposnsabilidades() {
		return listaReposnsabilidades;
	}

	public void setListaReposnsabilidades(
			List<RelatorioResponsabilidades> listaReposnsabilidades) {
		this.listaReposnsabilidades = listaReposnsabilidades;
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
