package br.com.mobiew.siseve.model.enuns;

public enum CorEnum {
    AMARELO ( 1, "Amarelo" ),
    AZUL    ( 2, "Azul" ),
    BEGE    ( 3, "Bege" ),
    BRANCO  ( 4, "Branco" ),
    BRONZE  ( 5, "Bronze" ),
    CINZA   ( 6, "Cinza" ),
    DOURADO ( 7, "Dourado" ),
    LARANJA ( 8, "Laranja" ),
    MARROM  ( 9, "Marrom" ),
    PRATA   ( 10, "Prata" ),
    PRETO   ( 11, "Preto" ),
    ROSA    ( 12, "Rosa" ),
    ROXO    ( 13, "Roxo" ),
    VERDE   ( 14, "Verde" ),
    VERMELHO( 15, "Vermelho" ),
    VINHO   ( 16, "Vinho" );

    private final Integer codigo;

    private final String nome;

    private CorEnum( Integer codigo, String nome ) {
        this.codigo = codigo;
        this.nome = nome;
    }

    /**
     * Código
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Nome
     */
    public String getNome() {
        return nome;
    }

    public static CorEnum getEnumByNome( final String value ) {
        for ( CorEnum enu: CorEnum.values() ) {
            if ( enu.getNome().equals( value ) ) {
                return enu;
            }
        }
        return null;
    }

    public static CorEnum getEnumByCodigo( final String value ) {
        for ( CorEnum enu: CorEnum.values() ) {
            if ( enu.getCodigo().equals( value ) ) {
                return enu;
            }
        }
        return null;
    }
}
