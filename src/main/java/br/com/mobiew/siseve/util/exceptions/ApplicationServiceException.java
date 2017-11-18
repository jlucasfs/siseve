package br.com.mobiew.siseve.util.exceptions;

public class ApplicationServiceException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ApplicationServiceException( Throwable e ) {
        super( e );
    }

    public ApplicationServiceException( String msg, Throwable e ) {
        super( msg, e );
    }

    public ApplicationServiceException( String msg ) {
        super( msg );

    }

    public ApplicationServiceException( String msg, Throwable e, Long cod ) {
        super( msg, e, cod );

    }

    public ApplicationServiceException( String msg, Long cod ) {
        super( msg, cod );

    }

}
