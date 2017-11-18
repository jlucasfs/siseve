package br.com.mobiew.siseve.model.enuns;

public enum PeriodoEnum {
    
	MES( "Meses" ), 
    DIA( "Dias" ), 
    SEMANA( "Semanas" ), 
    ANO( "Ano" );

    private String descricao;

    private PeriodoEnum( String descricaoParam ) {
        this.descricao = descricaoParam;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
