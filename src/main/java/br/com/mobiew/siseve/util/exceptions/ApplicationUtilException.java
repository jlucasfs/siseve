package br.com.mobiew.siseve.util.exceptions;

public class ApplicationUtilException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ApplicationUtilException( String msg ) {
        super( msg );
    }

    public ApplicationUtilException( String msg, Throwable e ) {
        super( msg, e );
    }

    public ApplicationUtilException( String msg, Throwable e, Long cod ) {
        super( msg, e, cod );
    }

    public ApplicationUtilException( String msg, Long cod ) {
        super( msg, cod );
    }

    public ApplicationUtilException( Throwable e ) {
        super( e );
    }

}
