package br.com.mobiew.siseve.util.exceptions;

import br.com.mobiew.siseve.util.IApplicationConstantsErrors;

public class ApplicationInvalidSessionServiceException extends ApplicationServiceException {

    private static final long serialVersionUID = 1L;

    public ApplicationInvalidSessionServiceException() {
        super( IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION, IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION_CODE );
    }

    public ApplicationInvalidSessionServiceException( Long cod ) {
        super( IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION, cod );
    }

    public ApplicationInvalidSessionServiceException( String msg ) {
        super( msg, IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION_CODE );
    }

    public ApplicationInvalidSessionServiceException( Throwable e ) {
        super( IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION, e, IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION_CODE );
    }

    public ApplicationInvalidSessionServiceException( String msg, Long cod ) {
        super( msg, cod );
    }

    public ApplicationInvalidSessionServiceException( String msg, Throwable e ) {
        super( msg, e, IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION_CODE );
    }

    public ApplicationInvalidSessionServiceException( Throwable e, Long cod ) {
        super( IApplicationConstantsErrors.INVALID_SESSION_EXCEPTION, e, cod );
    }

    public ApplicationInvalidSessionServiceException( String msg, Throwable e, Long cod ) {
        super( msg, e, cod );
    }
}
