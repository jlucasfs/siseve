package br.com.mobiew.siseve.util.exceptions;

public class ApplicationServiceAttachmentException extends ApplicationServiceException {

	private static final long serialVersionUID = 1L;

	public ApplicationServiceAttachmentException(String msg) {
		super(msg);
	}
	
	public ApplicationServiceAttachmentException( Throwable e ){  
		super(e);
	} 	

}
