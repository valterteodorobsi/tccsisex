package controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.primefaces.context.RequestContext;

import model.PedidoDeExame;
import dao.PedidoDeExameDao;
import dao.PedidoDeExameDaoImp;


@ManagedBean(name = "pedidoExameBen")
@ViewScoped
public class PedidoExameController {
	
	private static PrintService impressora;  
	private PedidoDeExame pedidoDeExame;

	
	@PostConstruct
	public void init() {
		atribuirEstadoInicial();
	}

	private void atribuirEstadoInicial() {

		pedidoDeExame = new PedidoDeExame();

	}
	
	public static PrintService getImpressora() {
		return impressora;
	}

	public static void setImpressora(PrintService impressora) {
		PedidoExameController.impressora = impressora;
	}
	
	
	public PedidoDeExame getPedidoDeExame() {
		return pedidoDeExame;
	}

	public void setPedidoDeExame(PedidoDeExame pedidoDeExame) {
		this.pedidoDeExame = pedidoDeExame;
	}
	
	public String adicionarPedidoExame() {
		PedidoDeExameDao dao = new PedidoDeExameDaoImp();
		dao.save(pedidoDeExame);
		info();
		return "/home.jsf";
	}
	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !",
						"Pedido Realizado com Sucesso. "));
	}
	
	//JS = JavaScrip, esse metodo é chamado na view para chamar a função java scrip do view
	//vou verificar se existe parametra pois essa função imprime a tela do sistema e porederia ser um formulario.
	public void imprimirJS(){

        RequestContext.getCurrentInstance().execute("imprimirJS();");
    }
	
	// Verificar esse metodo pois não funciono  Acredito que terá que excluir pois não estou chamando esse metodo.
	public void Imprimir(PedidoDeExame pedidoDeExame){
		 detectaImpressoras(); 
	}
	public void detectaImpressoras() {  
		  
        try {  
  
            DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;  
            PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);  
            for (PrintService p: ps) {  
  
                System.out.println("Impressora encontrada: " + p.getName());  
  
                if (p.getName().contains("Text") || p.getName().contains("Generic"))  {  
  
                    System.out.println("Impressora Selecionada: " + p.getName());  
                    setImpressora(p);  
                    break;  
  
                }  
  
            }  
  
        } catch (Exception e) {  
  
            e.printStackTrace();  
  
        }  
	}

	
}	
