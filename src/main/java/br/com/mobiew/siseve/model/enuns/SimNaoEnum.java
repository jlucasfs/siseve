package br.com.mobiew.siseve.model.enuns;

public enum SimNaoEnum {

    S( "Sim" ),
    N( "Não" );

    private final String resposta;

    SimNaoEnum( final String respostaParam ) {
        this.resposta = respostaParam;
    }

    public String getResposta() {
        return this.resposta;
    }
    
    public static SimNaoEnum getEnumFromValue( final String value ) {
        for ( SimNaoEnum simNao: SimNaoEnum.values() ) {
            if ( simNao.getResposta().equals( value ) ) {
                return simNao;
            }
        }
        return null;
    }

}
