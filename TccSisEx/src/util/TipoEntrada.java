package util;

public enum TipoEntrada {
	
	EMERGENCIAL("Emergencial"),
	ELETIVO("Eletivo");
	
	private String descricao;
	
	TipoEntrada(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public static TipoEntrada getByOrdinal(int ordinal){
		TipoEntrada tipo = null;
		for(TipoEntrada tipoEntrada : TipoEntrada.values()){
			if(tipoEntrada.ordinal() == ordinal){
				tipo = tipoEntrada;
			}
		}
		
		return tipo;
	}

}
