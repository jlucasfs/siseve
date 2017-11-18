package br.com.mobiew.siseve.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Classe  responsavel por metodos helper para tratamento/validacao de email
 * 
 * @author Cleilson
 *
 */
public final class EmailUtil {
 
    private static String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
	private static String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)*";
	private static String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

	private static Pattern pattern = Pattern.compile(
			"^" + ATOM + "+(\\." + ATOM + "+)*@"
					+ DOMAIN
					+ "|"
					+ IP_DOMAIN
					+ ")$",
			Pattern.CASE_INSENSITIVE
	);

	public static boolean isValid( String value ) {
		if ( value == null || value.length() == 0 ) {
			return false;
		}
		Matcher m = pattern.matcher( value );
		return m.matches();
	}
}
