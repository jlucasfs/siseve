package br.com.mobiew.siseve.model.enuns;

public enum StatusEnum {
	
	A( "Aberto" ),
	F( "Fechado" );
	
	private final String descricao;

	private StatusEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		 return this.descricao;
    }
    
    public String getAbreviacao() {
    	if(descricao == null){
    		return this.descricao;
    	}
    	if(descricao.equals("Aberto")){
    		return "A";
    	}
    	if(descricao.equals("Fechado")){
    		return "F";
    	}
        return this.descricao;
    }
    
    public static StatusEnum getStatusByDescricao(String descricao){
    	StatusEnum status = null;
    	for(StatusEnum statusEnum : StatusEnum.values()){
    		if(statusEnum.getDescricao().equals(descricao)){
    			status = statusEnum;
    			break;
    		}
    	}
    	return status;
    }

}
