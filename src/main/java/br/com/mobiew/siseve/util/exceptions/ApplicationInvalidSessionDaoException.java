package br.com.mobiew.siseve.util.exceptions;

import br.com.mobiew.siseve.util.IApplicationConstantsErrors;

public class ApplicationInvalidSessionDaoException extends ApplicationDaoException {
	
private static final long serialVersionUID = 1L;
	
	public ApplicationInvalidSessionDaoException() {
		super(IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION, IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION_CODE);
	}

	public ApplicationInvalidSessionDaoException(String msg) {
		super(msg, IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION_CODE);  
	}
	
	public ApplicationInvalidSessionDaoException(Long cod) {
		super(IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION , cod);  
	}

	public ApplicationInvalidSessionDaoException(Throwable e) {
		super(IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION, e, IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION_CODE);
	}

	public ApplicationInvalidSessionDaoException(String msg, Long cod) {
		super(msg , cod);
	}

	public ApplicationInvalidSessionDaoException(String msg, Throwable e) {
		super(msg, e ,IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION_CODE);  
	}

	public ApplicationInvalidSessionDaoException(Throwable e, Long cod) {
		super(IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION, e , cod);  
	}
	
	public ApplicationInvalidSessionDaoException(String msg, Throwable e, Long cod) {
		super(msg , e , cod); 
	}
}
