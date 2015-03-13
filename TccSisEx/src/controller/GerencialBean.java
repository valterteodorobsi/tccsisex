package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Gerencial;

import org.primefaces.model.chart.PieChartModel;

import util.TipoEntrada;
import dao.GerencialDao;

@ManagedBean(name = "gerencialBean1")
@ViewScoped
public class GerencialBean implements Serializable {

                /**
         * 
         */
        private static final long serialVersionUID = 1L;
        
                private Gerencial gerencial;
                private List<Gerencial> listaGerencial = new ArrayList<Gerencial>();
                private Date dataInicial;
                private Date dataFinal;
                
                
                private PieChartModel piechart;
                
                /*@PostConstruct
                public void init() {
                        atribuirEstadoInicial();
                }

                private void atribuirEstadoInicial() {

                        gerencial = new Gerencial();
                        

                }*/
                
                public void abreGrafico(){
                        if(gerencial != null){
                                
                        piechart = new PieChartModel();
                        GerencialDao dao = new GerencialDao();
                        String nomeMedico = gerencial.getNomeMedico();
                       
                        listaGerencial =  dao.relatorioGerencial(nomeMedico);
                                
                                piechart = new PieChartModel();
                                
                                for(Gerencial gerencial : listaGerencial){
                                        piechart.set(TipoEntrada.getByOrdinal( Integer.valueOf(gerencial.getTipoEntrada())).getDescricao(), gerencial.getQtdeEntrada());
                                }
                        }
                        
                }
                
                /*private void createPieModel1() {
                        String  nomeMedico = gerencial.getNomeMedico();
                        float emegencial = gerencial.getEmergencial();
                        
                piechart = new PieChartModel();
                GerencialDao dao = new GerencialDao();
                
                listaGerencial =  dao.relatorioGerencial(nomeMedico, emegencial);
               
                listaGerencial.get(1);
                gerencial.setEmergencial(emegencial);
                gerencial.setNomeMedico(nomeMedico);
                
                // ver aqui 
                piechart.set(nomeMedico, emegencial);
               // piechart.setTitle("Gerencial");
               // piechart.setLegendPosition("w");
            }
                */
                
                public GerencialBean () {
                        
                        piechart = new PieChartModel();
                        gerencial = new Gerencial();

                }
                
                public Gerencial getGerencial() {
                        return gerencial;
                }
                public void setGerencial(Gerencial gerencial) {
                        this.gerencial = gerencial;
                }
                public List<Gerencial> getListaGerencial() {
                        return listaGerencial;
                }
                public void setListaGerencial(List<Gerencial> listaGerencial) {
                        this.listaGerencial = listaGerencial;
                }
                public PieChartModel getPiechart() {
                        return piechart;
                }
                public void setPiechart(PieChartModel piechart) {
                        this.piechart = piechart;
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
                
                
                
}