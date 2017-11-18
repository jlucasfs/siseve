package br.com.mobiew.siseve.util.exceptions;

import br.com.mobiew.siseve.util.IApplicationConstantsErrors;


public class ApplicationIllegalArgumentServiceException extends ApplicationServiceException {

	private static final long serialVersionUID = 1L;
	
	public ApplicationIllegalArgumentServiceException() {
		super(IApplicationConstantsErrors.ILLEGAL_ARGUMENT_EXCEPTION, IApplicationConstantsErrors.ILLEGAL_ARGUMENT_EXCEPTION_CODE);
	}
	
	public ApplicationIllegalArgumentServiceException( String msg , Throwable e ){
		super(msg, e, IApplicationConstantsErrors.ILLEGAL_ARGUMENT_EXCEPTION_CODE);
	} 
	
	public ApplicationIllegalArgumentServiceException( String msg ){  
		super(msg, IApplicationConstantsErrors.ILLEGAL_ARGUMENT_EXCEPTION_CODE);
	} 
	
	public ApplicationIllegalArgumentServiceException( String msg , Throwable e , Long cod ){  
		super(msg , e , cod);  
	} 
	
	public ApplicationIllegalArgumentServiceException( String msg , Long cod ){  
		super(msg , cod);  
	}

	public ApplicationIllegalArgumentServiceException(Throwable e) {
		super(IApplicationConstantsErrors.ILLEGAL_ARGUMENT_EXCEPTION, e, IApplicationConstantsErrors.ILLEGAL_ARGUMENT_EXCEPTION_CODE);
	} 

}
