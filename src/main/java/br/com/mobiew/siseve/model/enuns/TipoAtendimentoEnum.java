package br.com.mobiew.siseve.model.enuns;

public enum TipoAtendimentoEnum {
    
    CONSULTA( "Consulta" ), 
    CIRURGIA( "Cirurgia" ), 
    ENCAIXE( "Encaixe" ), 
    ENCAMINHAMENTO( "Encaminhamento" ),
    EXAME( "Exame" ), 
    RECONSULTA( "Reconsulta" ),
    EMERGENCIA( "Emergência" ),
    ORTOGNATICA( "Ortognática" );

    private String descricao;

    private TipoAtendimentoEnum( String descricaoParam ) {
        this.descricao = descricaoParam;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
