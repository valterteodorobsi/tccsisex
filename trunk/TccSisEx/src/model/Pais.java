package model;

import java.util.Map;

public class Pais {
	private String nome;
	private Map<String, Integer> vendas;
	
	public Pais(String nome, Map<String, Integer> vendas) {
		super();
		this.nome = nome;
		this.vendas = vendas;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Map<String, Integer> getVendas() {
		return vendas;
	}
	public void setVendas(Map<String, Integer> vendas) {
		this.vendas = vendas;
	}
	public void putVenda(String ano, Integer valor){
		this.vendas.put(ano, valor);
	}
	
	public Integer getVenda(String ano){
		return this.vendas.get(ano);
	}
	
	
}