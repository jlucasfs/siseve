package br.com.mobiew.siseve.util.exceptions;


public class ApplicationIllegalArgumentUtilException extends ApplicationUtilException {

	private static final long serialVersionUID = 1L;

	public ApplicationIllegalArgumentUtilException(String message) {
		super(message);
	}

	public ApplicationIllegalArgumentUtilException(Throwable e) {
		super(e);
	}
}
