package br.com.mobiew.siseve.util.exceptions;

public class ApplicationFileNotFoundException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public ApplicationFileNotFoundException(String e , Long cod) {
		super( e , cod ); 
	}
	
	public ApplicationFileNotFoundException(String e) {
		super( e ); 
	}
}
