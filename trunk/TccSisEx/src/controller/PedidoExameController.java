package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.PedidoDeExame;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import dao.PedidoDeExameDao;
import dao.PedidoDeExameDaoImp;


@ManagedBean(name = "pedidoExameBen")
@ViewScoped
public class PedidoExameController {
	
	private PedidoDeExame pedidoDeExame;
	private List<PedidoDeExame> pedidoDeExames = new ArrayList<PedidoDeExame>();
	private String path; //Caminho base
	private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jarper
	public List<PedidoDeExame> getPedidoDeExames() {
		return pedidoDeExames;
	}

	public void setPedidoDeExames(List<PedidoDeExame> pedidoDeExames) {
		this.pedidoDeExames = pedidoDeExames;
	}

	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {

		pedidoDeExame = new PedidoDeExame();
		
	}
	
	
	
	public PedidoDeExame getPedidoDeExame() {
		return pedidoDeExame;
	}

	public void setPedidoDeExame(PedidoDeExame pedidoDeExame) {
		this.pedidoDeExame = pedidoDeExame;
	}
	
	public String adicionarPedidoExame() throws Exception {
		PedidoDeExameDao dao = new PedidoDeExameDaoImp();
		dao.save(pedidoDeExame);
		info();
		pedidoDeExames.add(pedidoDeExame);
		 

		imprimir(pedidoDeExames);
		return "/home.jsf";
	}
	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Pedido Realizado com Sucesso. "));
	}
	
		
	
	
	//Recupera os caminhos para que a classe possa encontrar os relatórios
	public PedidoExameController() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jasper/";
		System.out.println(path);
	}
	
	
	//Imprime/gera uma lista de Clientes
	public void imprimir(List<PedidoDeExame> pedidoDeExames) throws Exception	
	{
		JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "Pedido_Exame.jrxml");
		
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(pedidoDeExames));
		//abre visualizador  
        JasperViewer jv = new JasperViewer(print, false);  
        jv.setTitle("Pedido De Exame");    
        jv.setVisible(true); 
		
		JasperExportManager.exportReportToPdfFile(print, "c:/relatorio/Pedido_Exame.pdf");		
	}
 
	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}
	
	public String getPath() {
		return this.path;
	}
	

	
}	
