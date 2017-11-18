package br.com.mobiew.siseve.util.exceptions;

import br.com.mobiew.siseve.util.IApplicationConstantsErrors;

public class ApplicationDBConnectionException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public ApplicationDBConnectionException() {
		super(IApplicationConstantsErrors.DB_CONNECTION_EXCEPTION, IApplicationConstantsErrors.DB_CONNECTION_EXCEPTION_CODE);
	}
	
	public ApplicationDBConnectionException(Throwable e) {
		super(IApplicationConstantsErrors.DB_CONNECTION_EXCEPTION, e, IApplicationConstantsErrors.DB_CONNECTION_EXCEPTION_CODE); 
	}

	public ApplicationDBConnectionException(String msg) {
		super(msg, IApplicationConstantsErrors.DB_CONNECTION_EXCEPTION_CODE); 
	}

	public ApplicationDBConnectionException(Long cod) {
		super(IApplicationConstantsErrors.DB_CONNECTION_EXCEPTION, cod); 
	}

	public ApplicationDBConnectionException(String msg, Throwable e) {
		super(msg, e, IApplicationConstantsErrors.DB_CONNECTION_EXCEPTION_CODE); 
	}

	public ApplicationDBConnectionException(Throwable e , Long cod) {
		super(IApplicationConstantsErrors.DB_CONNECTION_EXCEPTION, e, cod); 
	}
	
	public ApplicationDBConnectionException( String msg , Long cod ) {  
        super(msg , cod);  
    }

	public ApplicationDBConnectionException(String msg, Throwable e, Long cod) {
		super(msg, e, cod); 
	}
}
