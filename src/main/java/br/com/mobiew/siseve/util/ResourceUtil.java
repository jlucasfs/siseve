package br.com.mobiew.siseve.util;

import groovy.util.ResourceException;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Classe ResourceUtil responsavel por fornecer metodos utilitarios ao sistema.
 *
 */
public final class ResourceUtil {

    /**
     * Constroi a instancia de 'ResourceUtil'.
     */
    private ResourceUtil() {
        super();
    }

    /**
     * Obter o valor da propriedade da arquivo de configuracao (config.properties).
     *
     * @param propriedade Propriedade a ser recuperada do arquivo de configuracao.
     * @return String O valor da propriedade informada.
     */
    public static String obterValorPropriedade( final String propriedade ) {
        return ResourceBundle.getBundle( Constantes.BUNDLE_CONFIG ).getString( propriedade );
    }

    /**
     * Obter o valor da propriedade da arquivo de configuracao (config.properties).
     *
     * @param propriedade Propriedade a ser recuperada do arquivo de configuracao.
     * @return String O valor da propriedade informada.
     */
    public static String obterValorPropriedadeResource( final String propriedade ) {
        return ResourceBundle.getBundle( Constantes.RESOURCES_CONFIG ).getString( propriedade );
    }


    /**
     * Retorna o recurso do arquivo de propriedades solicitado.
     *
     * @param key A palavra chave.
     * @return O valor do recurso do arquivo de propriedades.
     * @throws ResourceException ResourceException
     */
    public static String getResource( final String key ) {
        return ResourceBundle.getBundle( Constantes.BUNDLE_CONFIG ).getString( key );
    }

    /**
     * Retorna a mensagem de acordo com a propriedade informada e considerando os parametros.
     *
     * @param key A propriedade.
     * @param params Os parametros da mensagem.
     * @return A mensagem.
     * @throws ResourceException ResourceException
     */
    public static String getMessage( final String key, final Object[] params ) {
        MessageFormat formatter = new MessageFormat( "" );
        formatter.setLocale( new Locale( "pt" ) );
        formatter.applyPattern( getMessage( key ) );
        return formatter.format( params );
    }

    /**
     * Retorna a mensagem de acordo com a propriedade informada.
     *
     * @param key A propriedade.
     * @return A mensagem.
     * @throws ResourceException ResourceException
     */
    public static String getMessage( final String key ) {
        return ResourceBundle.getBundle( Constantes.BUNDLE_MESSAGES ).getString( key );
    }
}
