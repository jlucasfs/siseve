package br.com.mobiew.siseve.util.scopes;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

/**
 * Define o dominio de todos os escopos que a aplicacao pode utilizar
 * 
 * @author leandro
 * 
 */
public interface Scopes {

	/**
	 * instancia o bean somente uma vez por Spring Container
	 */
	final String SINGLETON = SCOPE_SINGLETON;

	/**
	 * Permite instanciar o bean várias vezes(uma vez por uso)
	 */
	final String PROTOTYPE = SCOPE_PROTOTYPE;

	/**
	 * Coloca o bean no contexto de Request
	 */
	final String REQUEST = "request";

	/**
	 * Coloca o bean no contexto de Session
	 */
	final String SESSION = "session";

	/**
	 * Coloca o bean no escopo de View, similar ao Flash scope do JSF2
	 */
	final String VIEW = "view";
}