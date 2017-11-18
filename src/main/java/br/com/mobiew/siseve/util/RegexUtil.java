package br.com.mobiew.siseve.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    /**
     * Retorna o texto de um grupo do nome do arquivo.
     * 
     * @param fileNameParam Nome do arquivo.
     * @param groupParam Grupo.
     * @return O texto do grupo solicitado.
     * @throws Exception Exception.
     */
    public static String getGroup( final String fileNameParam, final int groupParam ) throws Exception {
        String result = null;
        Matcher matcher = Pattern.compile( ImageFileFilter.REGEXP ).matcher( fileNameParam );
        if ( matcher.find() && matcher.groupCount() == 5 ) {
            result = matcher.group( groupParam );
        }
        return result;
    }
}
