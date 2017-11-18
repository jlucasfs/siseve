package br.com.mobiew.siseve.util.exceptions;

public class ApplicationConnectionException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public ApplicationConnectionException(String message, Throwable e) {
		super(message, e);
	}

	public ApplicationConnectionException(String message) {
		super(message);
	}
}
