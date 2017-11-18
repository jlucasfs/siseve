package br.com.mobiew.siseve.model.enuns;

public enum UFEnum {

    AC( "12", "Acre" ),
    AL( "27", "Alagoas" ),
    AP( "16", "Amapá" ),
    AM( "13", "Amazonas" ),
    BA( "29", "Bahia" ),
    CE( "23", "Ceará" ),
    DF( "53", "Distrito Federal" ),
    ES( "32", "Espírito Santo" ),
    GO( "52", "Goiás" ),
    MA( "21", "Maranhão" ),
    MT( "51", "Mato Grosso" ),
    MS( "50", "Mato Grosso do Sul" ),
    MG( "31", "Minas Gerais" ),
    PA( "15", "Pará" ),
    PB( "25", "Paraíba" ),
    PR( "41", "Paraná" ),
    PE( "26", "Pernambuco" ),
    PI( "22", "Piauí" ),
    RJ( "33", "Rio de Janeiro" ),
    RN( "24", "Rio Grande do Norte" ),
    RS( "43", "Rio Grande do Sul" ),
    RO( "11", "Rondônia" ),
    RR( "14", "Roraima" ),
    SC( "42", "Santa Catarina" ),
    SP( "35", "São Paulo" ),
    SE( "28", "Sergipe" ),
    TO( "17", "Tocantins" ),
    EX( "99", "Exterior" );

    private final String codigo;

    private final String nome;

    private UFEnum( String codigo, String nome ) {
        this.codigo = codigo;
        this.nome = nome;
    }

    /**
     * Código da UF conforme tabela do IBGE.
     */
    public String getCodigo() {
        return codigo;
    }

    /*
     * Nome da unidade da federação.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sigla da UF.
     */
    public String getUF() {
        return name();
    }

    /**
     * Obtém enum pela UF.
     */
    public static UFEnum getEnumByUF( String uf ) {
        for ( UFEnum e: values() ) {
            if ( e.getUF().equals( uf ) ) {
                return e;
            }
        }
        return null;
    }

    /**
     * Obtém enum pelo código da UF.
     */
    public static UFEnum getEnumByCodigo( String codigoParam ) {
        for ( UFEnum e: values() ) {
            if ( e.getCodigo().equals( codigoParam ) ) {
                return e;
            }
        }
        return null;
    }
}
