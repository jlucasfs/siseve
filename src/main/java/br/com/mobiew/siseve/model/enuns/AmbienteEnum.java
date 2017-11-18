package br.com.mobiew.siseve.model.enuns;

public enum AmbienteEnum {

    DSV( "dsv", "Desenvolvimento" ), 
    HMG( "hmg", "Homologacao" ), 
    PRD( "prd", "Producao" );

    private final String codigo;

    private final String descricao;

    private AmbienteEnum( final String codigoParam, final String descricaoParam ) {
        this.codigo = codigoParam;
        this.descricao = descricaoParam;
    }

    public static AmbienteEnum getEnumFromDescricao( final String value ) {
        for ( AmbienteEnum tipo: AmbienteEnum.values() ) {
            if ( tipo.getDescricao().equals( value ) ) {
                return tipo;
            }
        }
        return null;
    }

    public static AmbienteEnum getEnumFromCodigo( final String value ) {
        for ( AmbienteEnum tipo: AmbienteEnum.values() ) {
            if ( tipo.getCodigo().equals( value ) ) {
                return tipo;
            }
        }
        return null;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getCodigo() {
        return this.codigo;
    }
}
