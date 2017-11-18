package br.com.mobiew.siseve.model.enuns;

public enum TipoPessoaEnum {

    F( "Fisica" ), J( "Juridica" );

    private final String descricao;

    TipoPessoaEnum( final String descricaoParam ) {
        this.descricao = descricaoParam;
    }

    public String getDescricao() {
        return this.descricao;
    }

}
