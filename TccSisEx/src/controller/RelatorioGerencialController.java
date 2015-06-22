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

import org.primefaces.event.ItemSelectEvent;
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

	private String path; // Caminho base
	private String pathToReportPackage; // Caminho para o package onde estão
										// armazenados os relatorios Jarper
	private PieChartModel piechart;

	public RelatorioGerencialController() {
		gerencial = new RelatorioGerencial();
		piechart = new PieChartModel();
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jasper/";
		System.out.println(path);
	}

	public void imprimir() throws Exception {
		RelatorioGerencialDao dao = new RelatorioGerencialDao();
		int nomeMedico = gerencial.getNomeMedico();
		Date dataInicial = gerencial.getDataInicial();
		Date dataFinal = gerencial.getDataFinal();

		if (!dataFinal.after(dataInicial)) {
			infomracao();

		} else {
			if (gerencial.getNomeMedico() == 0) {

				listaGerencial = dao.relatorioGerencialTodos(nomeMedico,
						dataInicial, dataFinal);
			} else {

				listaGerencial = dao.relatorioGerencial(nomeMedico,
						dataInicial, dataFinal);
			}

			List<RelatorioGerencial> relatorioGerencial = new ArrayList<RelatorioGerencial>();

			for (RelatorioGerencial gerencial : listaGerencial) {
				if (gerencial.getTipoEntrada().equals("0")) {
					gerencial.setTipoEntrada("Emergencial");
					relatorioGerencial.add(gerencial);
				} else {
					gerencial.setTipoEntrada("Eletivo");
					relatorioGerencial.add(gerencial);
				}
				gerencial.setDataInicial(dataInicial);
				gerencial.setDataFinal(dataFinal);
			}

			if (listaGerencial.isEmpty()) {
				semRegistro();
			} else {
				JasperReport report = JasperCompileManager
						.compileReport(this.getPathToReportPackage()
								+ "Relatorio_Gerencial.jrxml");

				JasperPrint print = JasperFillManager.fillReport(report, null,
						new JRBeanCollectionDataSource(relatorioGerencial));
				// abre visualizador
				JasperViewer jv = new JasperViewer(print, false);
				jv.setTitle("Relatorio Gerencial");
				jv.setVisible(true);

				JasperExportManager.exportReportToPdfFile(print,
						"c:/relatorio/Relatorio_Gerencial.pdf");
			}
		}
	}

	public void abreGrafico() {

		if (gerencial.getNomeMedico() == 0) {
			warn();
		} else if (gerencial != null) {

 			piechart = new PieChartModel();
 			RelatorioGerencialDao dao = new RelatorioGerencialDao();
			Integer nomeMedico = gerencial.getNomeMedico();
			Date dataInicial = gerencial.getDataInicial();
			Date dataFinal = gerencial.getDataFinal();
			if (!dataFinal.after(dataInicial)) {
				infomracao();
			} else {
				listaGerencial = dao.relatorioGerencial(nomeMedico,
						dataInicial, dataFinal);

				piechart = new PieChartModel();

				for (RelatorioGerencial gerencial : listaGerencial) {
					piechart.set(
							TipoEntrada
									.getByOrdinal(
											Integer.valueOf(gerencial
													.getTipoEntrada()))
									.getDescricao(), gerencial.getQtdeEntrada());
					
				}
			}
		}
	}

	public void warn() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
								"Não é possivel gerar o grafico com o Valor 'TODOS'. "));
	}

	public void infomracao() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
						"A data final não pode ser menor que a data inicial. "));

	}
	public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex() +
        gerencial.getTipoEntrada());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

	public void semRegistro() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
						"Não existe registros"));
	}

}
