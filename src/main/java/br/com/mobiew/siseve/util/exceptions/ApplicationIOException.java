package br.com.mobiew.siseve.util.exceptions;

public class ApplicationIOException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public ApplicationIOException(String e , Long cod) {
		super( e , cod ); 
	}
	public ApplicationIOException(String e ) {
		super( e ); 
	}
}
