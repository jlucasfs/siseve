package br.com.mobiew.siseve.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Classe ConstantesNumero responsavel por manter as constantes de valores do tipo numerico.
 */
public final class ConstantesNumero {

    /**
     * Constroi a instancia de 'ConstantesNumero'.
     */
    private ConstantesNumero() {
        super();
    }

    public static final String FORMATO_NUMERO = "###,##0";

    public static final String FORMATO_NUMERO_INT = "00";

    /** Formato numero decimal inteiro. */
    public static final DecimalFormat FMT_NUM_DECIMAL_0 = new DecimalFormat( FORMATO_NUMERO );

    /** Formato numero decimal com uma casa decimal. */
    public static final DecimalFormat FMT_NUM_DECIMAL_1 = new DecimalFormat( FORMATO_NUMERO + ".0" );

    /** Formato numero decimal com duas casas decimais. */
    public static final DecimalFormat FMT_NUM_DECIMAL_2 = new DecimalFormat( FORMATO_NUMERO + ".00" );

    /** Formato numero decimal com tres casas decimais. */
    public static final DecimalFormat FMT_NUM_DECIMAL_3 = new DecimalFormat( FORMATO_NUMERO + ".000" );

    /** Formato numero decimal com quatro casas decimais. */
    public static final DecimalFormat FMT_NUM_DECIMAL_4 = new DecimalFormat( FORMATO_NUMERO + ".0000" );

    /** Format numero inteiro com duas posicoes. */
    public static final DecimalFormat FMT_NUM_INT = new DecimalFormat( FORMATO_NUMERO_INT );

    public static final String FORMATO_NUMERO_LONG8 = "00000000";

    public static final String FORMATO_NUMERO_LONG11 = "00000000000";

    public static final String FORMATO_NUMERO_LONG12 = "000000000000";

    public static final String FORMATO_NUMERO_LONG20 = "00000000000000000000";

    /** Format numero inteiro com oito posicoes. */
    public static final DecimalFormat FMT_NUM_LONG8 = new DecimalFormat( FORMATO_NUMERO_LONG8 );

    /** Format numero inteiro com onze posicoes. */
    public static final DecimalFormat FMT_NUM_LONG11 = new DecimalFormat( FORMATO_NUMERO_LONG11 );

    /** Format numero inteiro com doze posicoes. */
    public static final DecimalFormat FMT_NUM_LONG12 = new DecimalFormat( FORMATO_NUMERO_LONG12 );
    
    /** Format numero inteiro com vinte posicoes. */
    public static final DecimalFormat FMT_NUM_LONG20 = new DecimalFormat( FORMATO_NUMERO_LONG20 );

    public static final Locale LOCALE_PT_BR = new Locale( "pt", "BR" );

    public static final NumberFormat NUMBER_FORMAT_BR = new DecimalFormat( "#,##0.00", new DecimalFormatSymbols( LOCALE_PT_BR ) );

}
